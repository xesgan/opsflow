package org.example.services;

import org.example.models.WorkOrder;
import org.example.repositories.WorkOrderRepository;
import org.springframework.stereotype.Service;

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

    public WorkOrder findWorkOrder(UUID id){

        return workOrderRepository.getWorkOrderById(id);
    }
}
