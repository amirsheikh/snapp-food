package com.example.snappfood.mapper;

import com.example.snappfood.entity.Agent;
import com.example.snappfood.models.AgentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentMapper extends AbstractMapper<Agent, AgentModel> {
}
