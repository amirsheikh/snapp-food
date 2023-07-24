package com.example.snappfood.controller.dto;

import com.example.snappfood.models.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class OrderLightDTO {

    private String uid;
    private Date actionNeedDate;
    private long delayInMin;

    public static OrderLightDTO of(OrderModel model) {
        return new OrderLightDTO(model.getExtuid(), model.getActionNeedDate(), model.getDelayInMin());
    }
}
