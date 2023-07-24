package com.example.snappfood.entity;

import jakarta.persistence.Entity;
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
@Table(name = "CUSTOMERS")
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractEntity {

    @NotNull
    private String name;
    @NotNull
    private long balance;

}
