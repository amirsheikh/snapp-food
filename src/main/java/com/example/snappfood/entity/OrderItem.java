package com.example.snappfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "ORDER_ITEMS")
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends AbstractEntity {

    @NotNull
    private String name;
    @NotNull
    private int count;
    @NotNull
    private long unitPrice;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;
}
