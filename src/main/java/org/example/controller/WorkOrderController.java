package org.example.controller;

import org.example.models.WorkOrder;
import org.example.services.WorkOrderService;
import org.springframework.http.HttpStatus;
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

    record AssignOrderRequest(String assignedTo) {
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
    public List<WorkOrder> getAllWorkOrders() {
        return workOrderService.findAllWorkOrders();
    }

    @PatchMapping("/api/work-orders/{id}/assignment")
    public ResponseEntity<WorkOrder> assignOrder(@PathVariable UUID id, @RequestBody AssignOrderRequest request) {
        try {
            Optional<WorkOrder> order = workOrderService.assignWorkOrder(id, request.assignedTo);
            if (order.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(order.get());

        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/inprogress")
    public ResponseEntity<WorkOrder> startOrder(@PathVariable UUID id) {
        try {
            Optional<WorkOrder> order = workOrderService.startOrder(id);
            if (order.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(order.get());

        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/resolve")
    public ResponseEntity<WorkOrder> resolveOrder(@PathVariable UUID id) {
        try {
            Optional<WorkOrder> order = workOrderService.resolveOrder(id);
            if (order.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(order.get());

        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/close")
    public ResponseEntity<WorkOrder> closeOrder(@PathVariable UUID id) {
        try {
            Optional<WorkOrder> order = workOrderService.closeOrder(id);
            if (order.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(order.get());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/cancel")
    public ResponseEntity<WorkOrder> cancelOrder(@PathVariable UUID id) {
        try {
            Optional<WorkOrder> order = workOrderService.cancelOrder(id);
            if (order.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(order.get());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
