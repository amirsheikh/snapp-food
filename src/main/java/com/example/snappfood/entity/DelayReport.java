package com.example.snappfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "DELAY_REPORTS")
public class DelayReport extends AbstractEntity {

    private int delayInMin;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;


}
