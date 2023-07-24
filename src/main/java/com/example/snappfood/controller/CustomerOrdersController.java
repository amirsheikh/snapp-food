package com.example.snappfood.controller;

import com.example.snappfood.component.Messages;
import com.example.snappfood.exceptions.order.DelayedReportedBeforeException;
import com.example.snappfood.exceptions.order.OrderNotDelayedException;
import com.example.snappfood.exceptions.order.OrderNotFoundException;
import com.example.snappfood.models.DelayReportResult;
import com.example.snappfood.service.DelayReportService;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cu/v1/orders")
public class CustomerOrdersController {

    private final DelayReportService delayReportService;
    private final Messages messages;

    public CustomerOrdersController(DelayReportService delayReportService,
                                    Messages messages) {
        this.delayReportService = delayReportService;
        this.messages = messages;
    }

    @PostMapping("{orderUid}/report-delay")
    public DelayReportResult reportDelay(@PathVariable String orderUid) {
        try {
            return delayReportService.reportDelay(orderUid);
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    messages.getMessage("order.not.found"));
        } catch (OrderNotDelayedException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(406),
                    messages.getMessage("order.not.delayed"));
        } catch (DelayedReportedBeforeException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(406),
                    messages.getMessage("order.delayed.reported.before"));
        }
    }
}
