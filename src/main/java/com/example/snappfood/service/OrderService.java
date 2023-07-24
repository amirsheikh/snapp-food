package com.example.snappfood.service;

import com.example.snappfood.entity.Order;
import com.example.snappfood.exceptions.order.DelayedReportedBeforeException;
import com.example.snappfood.exceptions.order.OrderNotDelayedException;
import com.example.snappfood.exceptions.order.OrderNotFoundException;
import com.example.snappfood.mapper.AbstractMapper;
import com.example.snappfood.mapper.OrderMapper;
import com.example.snappfood.models.OrderModel;
import com.example.snappfood.repository.AbstractEntityRepository;
import com.example.snappfood.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService extends AbstractService<Order, OrderModel> {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    AbstractEntityRepository<Order> getRepository() {
        return orderRepository;
    }

    @Override
    AbstractMapper<Order, OrderModel> getMapper() {
        return orderMapper;
    }

    @Override
    public OrderModel update(OrderModel model) {
        //TODO This should be fix for new orders;
        Order order = orderRepository.findByExtuid(model.getExtuid()).orElseThrow();
        order.setActionNeedDate(model.getActionNeedDate());
        order.setDeliveryTimeInMin(model.getDeliveryTimeInMin());
        order.setLastUpdateDate(new Date());
        getRepository().save(order);
        return findOneMapper(order);
    }

    public Order get(OrderModel model) {
        return getRepository().findByExtuid(model.getExtuid()).orElse(null);
    }

    public OrderModel validateOrderForDelayReporting(String orderUid) throws OrderNotDelayedException,
            OrderNotFoundException, DelayedReportedBeforeException {
        OrderModel order = findOne(orderUid)
                .orElseThrow(OrderNotFoundException::new);
        validateOrderForDelayReporting(order);
        return order;
    }

    public OrderModel getFirstDelayedOrder() throws OrderNotFoundException {
        Order order = orderRepository
                .findFirstByAgentIsNullAndActionNeedDateIsNotNullOrderByActionNeedDateAsc();
        if (order == null) {
            throw new OrderNotFoundException();
        }
        return findOneMapper(order);
    }

    private void validateOrderForDelayReporting(OrderModel order) throws
            OrderNotDelayedException, DelayedReportedBeforeException {
        if (!order.isDelayed()) {
            throw new OrderNotDelayedException();
        }
        if (order.getActionNeedDate() != null) {
            throw new DelayedReportedBeforeException();
        }
    }
}
