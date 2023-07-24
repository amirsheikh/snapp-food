package com.example.snappfood.mapper;

import com.example.snappfood.entity.Order;
import com.example.snappfood.models.OrderModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends AbstractMapper<Order, OrderModel> {
}
