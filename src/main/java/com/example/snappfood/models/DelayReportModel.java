package com.example.snappfood.models;

import com.example.snappfood.entity.DelayReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class DelayReportModel extends AbstractModel<DelayReport> {

    private OrderModel order;
    private int delayInMin;

}
