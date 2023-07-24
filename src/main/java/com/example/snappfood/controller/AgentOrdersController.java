package com.example.snappfood.controller;

import com.example.snappfood.component.Messages;
import com.example.snappfood.controller.dto.OrderLightDTO;
import com.example.snappfood.exceptions.agent.AgentNotFoundException;
import com.example.snappfood.exceptions.agent.AgentTaskAssignedBeforeException;
import com.example.snappfood.exceptions.order.OrderNotFoundException;
import com.example.snappfood.models.VendorDelayModel;
import com.example.snappfood.service.DelayReportService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ag/v1/orders")
public class AgentOrdersController {

    private final DelayReportService delayReportService;
    private final Messages messages;

    public AgentOrdersController(DelayReportService delayReportService, Messages messages) {
        this.delayReportService = delayReportService;
        this.messages = messages;
    }

    @GetMapping("{agentUid}/assign")
    public OrderLightDTO assign(@PathVariable String agentUid) {
        try {
            return OrderLightDTO.of(delayReportService.assignOrder(agentUid));
        } catch (AgentTaskAssignedBeforeException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(406),
                    messages.getMessage("agent.task.assigned.before"));
        } catch (AgentNotFoundException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    messages.getMessage("agent.not.found"));
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    messages.getMessage("order.not.found"));
        }
    }

    @GetMapping("delay/per-vendor")
    public List<VendorDelayModel> getDelayPerVendor() {
        return delayReportService.getDelayPerVendor();
    }
}
