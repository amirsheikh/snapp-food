package com.example.snappfood.repository;

import com.example.snappfood.entity.Order;

public interface OrderRepository extends AbstractEntityRepository<Order> {

    Order findFirstByAgentIsNullAndActionNeedDateIsNotNullOrderByActionNeedDateAsc();
}
