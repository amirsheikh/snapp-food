package com.example.snappfood.models;

import com.example.snappfood.entity.Agent;
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
public class AgentModel extends AbstractModel<Agent> {
    private String name;
    private OrderModel assignedOrder;
}
