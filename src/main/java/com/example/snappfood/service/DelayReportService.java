package com.example.snappfood.service;

import com.example.snappfood.entity.DelayReport;
import com.example.snappfood.exceptions.agent.AgentNotFoundException;
import com.example.snappfood.exceptions.agent.AgentTaskAssignedBeforeException;
import com.example.snappfood.exceptions.order.DelayedReportedBeforeException;
import com.example.snappfood.exceptions.order.OrderNotDelayedException;
import com.example.snappfood.exceptions.order.OrderNotFoundException;
import com.example.snappfood.integrations.mocky.MockyRestService;
import com.example.snappfood.integrations.mocky.dto.MockyResponseDTO;
import com.example.snappfood.mapper.AbstractMapper;
import com.example.snappfood.mapper.DelayReportMapper;
import com.example.snappfood.models.AgentModel;
import com.example.snappfood.models.DelayReportModel;
import com.example.snappfood.models.DelayReportResult;
import com.example.snappfood.models.OrderModel;
import com.example.snappfood.models.VendorDelayModel;
import com.example.snappfood.models.status.TripStatus;
import com.example.snappfood.repository.AbstractEntityRepository;
import com.example.snappfood.repository.DelayReportRepository;
import com.example.snappfood.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DelayReportService  extends AbstractService<DelayReport, DelayReportModel> {

    private final DelayReportRepository delayReportRepository;
    private final DelayReportMapper delayReportMapper;
    private final OrderService orderService;
    private final MockyRestService mockyRestService;
    private final AgentService agentService;

    private final List<TripStatus> tripIngoingStatuses = List.of(TripStatus.AT_VENDOR,
            TripStatus.ASSIGNED, TripStatus.PICKED);

    private final String ASSIGN_TO_AGENT = "assign_to_agent";
    private final String NEW_ESTIMATE = "new_estimate";

    public DelayReportService(DelayReportRepository delayReportRepository,
                              OrderService orderService,
                              DelayReportMapper delayReportMapper,
                              AgentService agentService,
                              MockyRestService mockyRestService) {
        this.orderService = orderService;
        this.delayReportRepository = delayReportRepository;
        this.delayReportMapper = delayReportMapper;
        this.mockyRestService = mockyRestService;
        this.agentService = agentService;
    }

    @Override
    AbstractEntityRepository<DelayReport> getRepository() {
        return delayReportRepository;
    }

    @Override
    AbstractMapper<DelayReport, DelayReportModel> getMapper() {
        return delayReportMapper;
    }

    @Override
    public DelayReportModel update(DelayReportModel model) {
        DelayReport delayReport = delayReportRepository
                .findByExtuid(model.getExtuid())
                .orElseThrow();
        delayReport.setDelayInMin(model.getDelayInMin());
        delayReport.setLastUpdateDate(new Date());
        getRepository().save(delayReport);
        return findOneMapper(delayReport);
    }

    public DelayReportModel saveOrUpdate(DelayReportModel model) {
        DelayReport delayReport = delayReportRepository
                .findByExtuid(model.getExtuid())
                .orElse(makeNew(model));
        delayReport.setDelayInMin(model.getDelayInMin());
        delayReport.setLastUpdateDate(new Date());
        getRepository().save(delayReport);
        return findOneMapper(delayReport);
    }

    private DelayReport makeNew(DelayReportModel model) {
        orderService.findOne(model.getOrder().getExtuid());
        return DelayReport.builder()
                .order(orderService.getEntity(model.getOrder()))
                .delayInMin(model.getDelayInMin()).build();

    }

    @Transactional
    public DelayReportResult reportDelay(String orderUid) throws OrderNotFoundException,
            OrderNotDelayedException, DelayedReportedBeforeException {
        String action;
        OrderModel orderModel = orderService.validateOrderForDelayReporting(orderUid);
        DelayReportModel delayReportModel = DelayReportModel.builder()
                .delayInMin((int) orderModel.getDelayInMin())
                .order(orderModel).build();
        if (orderModel.getTrip() != null && tripIngoingStatuses
                .contains(orderModel.getTrip().getStatus())) {
            action = processReportWithIngoingTrip(orderModel);
        } else {
            action = processReportWithoutIngoingTrip(orderModel);
        }
        this.saveOrUpdate(delayReportModel);
        orderService.update(delayReportModel.getOrder());
        return new DelayReportResult(action, DateUtils.toLocal(orderModel.getDeliveryDate()));
    }

    @Transactional
    public OrderModel assignOrder(String agentUid) throws AgentTaskAssignedBeforeException, AgentNotFoundException,
            OrderNotFoundException {
        AgentModel agentModel = agentService.validateAgentForDelayReportAssigning(agentUid);
        OrderModel orderModel = orderService.getFirstDelayedOrder();
        agentModel.setAssignedOrder(orderModel);
        orderService.update(orderModel);
        agentService.update(agentModel);
        return orderModel;
    }

    public List<VendorDelayModel> getDelayPerVendor() {
        return delayReportRepository.getDelayPerVendor();
    }

    private String processReportWithIngoingTrip(OrderModel order) {
        MockyResponseDTO responseDTO = mockyRestService.getNewEstimateTime();
        int newEstimatedTimeInMin = order.getDeliveryTimeInMin() + (int) order.getDelayInMin() +
                        responseDTO.getData().getEstimate();
        order.setDeliveryTimeInMin(newEstimatedTimeInMin);
        return NEW_ESTIMATE;
    }

    private String processReportWithoutIngoingTrip(OrderModel order) {
        order.setActionNeedDate(new Date());
        return ASSIGN_TO_AGENT;
    }
}
