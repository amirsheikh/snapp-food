package com.example.snappfood.models;

import com.example.snappfood.entity.OrderItem;
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
public class OrderItemModel extends AbstractModel<OrderItem> {
    private long unitPrice;
    private int count;
    private String name;

}
