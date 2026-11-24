package org.example.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorkOrderTest {

    @Test
    void shouldCreateWorkOrderWithOpenStatus() {
        WorkOrder workOrder = new WorkOrder("Test creación de WorkOrder en Test", "Esta Test verificará que el test funciona y puede crear una orden");
        Assertions.assertEquals(WorkOrderStatus.OPEN, workOrder.getStatus());
    }

    @Test
    void shouldCloseResolvedWorKOrder() {
        WorkOrder workOrder = new WorkOrder("Test cerrar WorkOrder en Test", "Esta Test verificará que el test puede cerrar una order");
        workOrder.assign("Elias");
        workOrder.inProgress();
        workOrder.resolve();
        workOrder.close();
        Assertions.assertEquals("Elias", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.CLOSED, workOrder.getStatus());
    }

    @Test
    void shouldAssignOpenWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test asignacion de Open Work Order Test", "Esta Test verificará que el test puede asignar una orden abierta");
        workOrder.assign("Julia");
        Assertions.assertEquals("Julia", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.ASSIGNED, workOrder.getStatus());
    }

    @Test
    void shouldStartAssignedWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test empezar orden asignada", "Esta Test verificará que el test puede empezar una orden asignada");
        workOrder.assign("Jesus");
        workOrder.inProgress();
        Assertions.assertEquals("Jesus", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.IN_PROGRESS, workOrder.getStatus());
    }

    @Test
    void shouldResolveInProgressWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test resolver orden empezada", "Esta Test verificará que el test puede resolver una orden empezada");
        workOrder.assign("Pablo");
        workOrder.inProgress();
        workOrder.resolve();
        Assertions.assertEquals("Pablo", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.RESOLVED, workOrder.getStatus());
    }

    @Test
    void shouldCancelOpenWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test cancelar orden abierta", "Esta Test verificará que el test puede cancelar una orden abierta");
        workOrder.cancel();
        Assertions.assertEquals(WorkOrderStatus.CANCELLED, workOrder.getStatus());
    }

    @Test
    void shouldCancelAssignWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test cancelar orden asignada", "Esta Test verificará que el test puede cancelar una orden asignada");
        workOrder.assign("Juan");
        workOrder.cancel();
        Assertions.assertEquals("Juan", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.CANCELLED, workOrder.getStatus());
    }

    @Test
    void shouldCancelStartedWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test cancelar orden empezada", "Esta Test verificará que el test puede cancelar una orden empezada");
        workOrder.assign("Juanma");
        workOrder.inProgress();
        workOrder.cancel();
        Assertions.assertEquals("Juanma", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.CANCELLED, workOrder.getStatus());
    }

    @Test
    void shouldReopenResolvedWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test reabrir orden resuelta", "Esta Test verificará que el test puede reabrir una orden resuelta");
        workOrder.assign("Ariagnis");
        workOrder.inProgress();
        workOrder.resolve();
        workOrder.reopen();
        Assertions.assertEquals("Ariagnis", workOrder.getAssignedTo());
        Assertions.assertEquals(WorkOrderStatus.IN_PROGRESS, workOrder.getStatus());
    }

    @Test
    void shouldNotCloseOpenWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test no cerrar orden abierta", "Esta Test verificará que el test no puede cancelar una orden abierta");
        Assertions.assertThrows(IllegalStateException.class, () -> workOrder.close());
        Assertions.assertEquals(WorkOrderStatus.OPEN, workOrder.getStatus());
    }

    @Test
    void shouldNotStartOpenWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test no empezar orden abierta", "Esta Test verificará que el test no puede empezar una orden abierta sin asignar");
        Assertions.assertThrows(IllegalStateException.class, () -> workOrder.inProgress());
        Assertions.assertEquals(WorkOrderStatus.OPEN, workOrder.getStatus());
    }

    @Test
    void shouldNotResolveOpenWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test no resolver orden abierta", "Esta Test verificará que el test no puede resolver una orden abierta sin asignar ni sin estar empezada");
        Assertions.assertThrows(IllegalStateException.class, () -> workOrder.resolve());
        Assertions.assertEquals(WorkOrderStatus.OPEN, workOrder.getStatus());
    }

    @Test
    void shouldNotCancelClosedWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test no cancelar orden cerrada", "Esta Test verificará que el test no puede cancelar una orden cerrada");
        workOrder.assign("Simeon");
        workOrder.inProgress();
        workOrder.resolve();
        workOrder.close();
        Assertions.assertEquals("Simeon", workOrder.getAssignedTo());
        Assertions.assertThrows(IllegalStateException.class, () -> workOrder.cancel());
        Assertions.assertEquals(WorkOrderStatus.CLOSED, workOrder.getStatus());
    }

    @Test
    void shouldNotReopenCancelledWorkOrder() {
        WorkOrder workOrder = new WorkOrder("Test no reabrir orden cancelada", "Esta Test verificará que el test no puede reabrir una orden cancelada");
        workOrder.assign("Josep");
        workOrder.cancel();
        Assertions.assertEquals("Josep", workOrder.getAssignedTo());
        Assertions.assertThrows(IllegalStateException.class, () -> workOrder.reopen());
        Assertions.assertEquals(WorkOrderStatus.CANCELLED, workOrder.getStatus());
    }
}
