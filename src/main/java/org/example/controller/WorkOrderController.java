package org.example.controller;

import org.example.dto.AssignOrderRequest;
import org.example.dto.CreateWorkOrderRequest;
import org.example.dto.WorkOrderResponse;
import org.example.models.WorkOrder;
import org.example.services.WorkOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class WorkOrderController {

    final private WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @PostMapping("/api/work-orders")
    public WorkOrderResponse postWorkOrder(@RequestBody CreateWorkOrderRequest request) {
        return toResponse(workOrderService.crearWorkOrder(request.title(), request.description()));
    }

    // Optional<WorkOrder> order = workOrderService.findWorkOrder(id)
    // Le pide al servicio que busque la orden. El Optional puede contener la orden o estar vacío.
    @GetMapping("/api/work-orders/{id}")
    public ResponseEntity<WorkOrderResponse> getWorkOrder(@PathVariable UUID id) {
        return workOrderService.findWorkOrder(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/work-orders")
    public List<WorkOrderResponse> getAllWorkOrders() {
        return workOrderService.findAllWorkOrders().stream()
                .map(this::toResponse)
                .toList();
    }

    @PatchMapping("/api/work-orders/{id}/assignment")
    public ResponseEntity<WorkOrderResponse> assignOrder(@PathVariable UUID id, @RequestBody AssignOrderRequest request) {
        try {
            return workOrderService.assignWorkOrder(id, request.assignedTo())
                    .map(this::toResponse)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/inprogress")
    public ResponseEntity<WorkOrderResponse> startOrder(@PathVariable UUID id) {
        try {
            return workOrderService.startOrder(id)
                    .map(this::toResponse)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/resolve")
    public ResponseEntity<WorkOrderResponse> resolveOrder(@PathVariable UUID id) {
        try {
            return workOrderService.resolveOrder(id)
                    .map(this::toResponse)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/close")
    public ResponseEntity<WorkOrderResponse> closeOrder(@PathVariable UUID id) {
        try {
            return workOrderService.closeOrder(id)
                    .map(this::toResponse)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/cancel")
    public ResponseEntity<WorkOrderResponse> cancelOrder(@PathVariable UUID id) {
        try {
            return workOrderService.cancelOrder(id)
                    .map(this::toResponse)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/api/work-orders/{id}/reopen")
    public ResponseEntity<WorkOrderResponse> reopenOrder(@PathVariable UUID id) {
        try {
            return workOrderService.reopenOrder(id)
                    .map(this::toResponse)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Auxiliar mapping method
    private WorkOrderResponse toResponse(WorkOrder workOrder) {
        return new WorkOrderResponse(
                workOrder.getId(),
                workOrder.getTitle(),
                workOrder.getDescription(),
                workOrder.getStatus(),
                workOrder.getCreatedAt(),
                workOrder.getAssignedTo()
        );
    }
}
