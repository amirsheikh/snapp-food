package com.example.snappfood.service;

import com.example.snappfood.entity.Agent;
import com.example.snappfood.entity.Order;
import com.example.snappfood.exceptions.agent.AgentNotFoundException;
import com.example.snappfood.exceptions.agent.AgentTaskAssignedBeforeException;
import com.example.snappfood.mapper.AbstractMapper;
import com.example.snappfood.mapper.AgentMapper;
import com.example.snappfood.models.AgentModel;
import com.example.snappfood.repository.AbstractEntityRepository;
import com.example.snappfood.repository.AgentRepository;
import org.springframework.stereotype.Service;

@Service
public class AgentService extends AbstractService<Agent, AgentModel> {

    private final AgentRepository agentRepository;
    private final AbstractMapper agentMapper;
    private final OrderService orderService;

    public AgentService(AgentRepository agentRepository, AgentMapper agentMapper,
                        OrderService orderService) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
        this.orderService = orderService;
    }

    @Override
    AbstractEntityRepository<Agent> getRepository() {
        return agentRepository;
    }

    @Override
    AbstractMapper<Agent, AgentModel> getMapper() {
        return agentMapper;
    }

    public AgentModel validateAgentForDelayReportAssigning(String agentUid) throws AgentNotFoundException,
            AgentTaskAssignedBeforeException {
        AgentModel agentModel = findOne(agentUid)
                .orElseThrow(AgentNotFoundException::new);
        if (agentModel.getAssignedOrder() != null) {
            throw new AgentTaskAssignedBeforeException();
        }
        return agentModel;
    }

    @Override
    public AgentModel update(AgentModel model) {
        Order assignedOrder = orderService.get(model.getAssignedOrder());
        Agent agent = agentRepository.findByExtuid(model.getExtuid()).orElseThrow();
        agent.setAssignedOrder(assignedOrder);
        agentRepository.save(agent);
        return findOneMapper(agent);
    }
}
