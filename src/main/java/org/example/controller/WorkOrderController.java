package org.example.controller;

import org.example.models.WorkOrder;
import org.example.services.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class WorkOrderController {

    final private WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderSerivce) {
        this.workOrderService = workOrderSerivce;
    }

    record CreateWorkOrderRequest(String title, String description) {
    }

    @PostMapping("/api/work-orders")
    public WorkOrder postWorkOrder(@RequestBody CreateWorkOrderRequest request) {
        return workOrderService.crearWorkOrder(request.title(), request.description());
    }

    // Optional<WorkOrder> order = workOrderService.findWorkOrder(id)
    // Le pide al servicio que busque la orden. El Optional puede contener la orden o estar vacío.
    @GetMapping("/api/work-orders/{id}")
    public ResponseEntity<WorkOrder> getWorkOrder(@PathVariable UUID id) {
        Optional<WorkOrder> order = workOrderService.findWorkOrder(id);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order.get());
    }

    @GetMapping("/api/work-orders")
    public List<WorkOrder> getAllWorkOrders(){
        return workOrderService.findAllWorkOrders();
    }
}
