package org.example.repositories;

import org.example.models.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class WorkOrderRepository {

    private Map<UUID, WorkOrder> workOrders = new HashMap<>();

    public void save(WorkOrder workOrder) {
        if(workOrder == null) {
            throw new IllegalArgumentException("workOrder is null");
        }
        workOrders.put(workOrder.getId(), workOrder);
    }

    public WorkOrder getWorkOrderById(UUID workOrderId) {
        return workOrders.get(workOrderId);
    }
}
