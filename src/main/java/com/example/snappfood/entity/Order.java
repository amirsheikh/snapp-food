package com.example.snappfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "ORDERS")
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractEntity {

    @NotNull
    private String status;
    @NotNull
    private long totalPrice;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trip trip;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<DelayReport> delayReports;

    private int deliveryTimeInMin;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vendor vendor;

    private Date actionNeedDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "assignedOrder")
    private Agent agent;

}
