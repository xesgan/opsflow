package org.example.services;

import org.example.models.WorkOrder;
import org.example.repositories.WorkOrderRepository;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkOrderService {

    private WorkOrderRepository workOrderRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public WorkOrder crearWorkOrder(String title, String description) {
        WorkOrder workOrder = new WorkOrder(title, description);
        workOrderRepository.save(workOrder);
        return workOrder;
    }

    public Optional<WorkOrder> assignWorkOrder(UUID id, String assignedTo){
        Optional<WorkOrder> order = findWorkOrder(id);
        if(order.isEmpty()){
            return Optional.empty();
        }
        WorkOrder workOrder = order.get();
        workOrder.assign(assignedTo);
        WorkOrder savedOrder = workOrderRepository.save(workOrder);
        return Optional.of(savedOrder);
    }

    public Optional<WorkOrder> findWorkOrder(UUID id){
        return workOrderRepository.findById(id);
    }

    public List<WorkOrder> findAllWorkOrders() {
        return workOrderRepository.findAll();
    }
}
