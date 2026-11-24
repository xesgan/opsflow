package org.example.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorkOrderTest {

    @Test
    void shouldCreateWorkOrderWithOpenStatus(){
        WorkOrder workOrder = new WorkOrder("Prueba creación de WorkOrder en Test", "Esta prueba verificará que el test funciona y puede crear una orden");
        Assertions.assertEquals(WorkOrderStatus.OPEN, workOrder.getStatus());
    }

    @Test
    void shouldCloseResolvedWorKOrder(){
        WorkOrder workOrder = new WorkOrder("Prueba cerrar WorkOrder en Test", "Esta prueba verificará que el test puede cerrar una order");
        workOrder.assign("Elias");
        workOrder.inProgress();
        workOrder.resolve();
        workOrder.close();
        Assertions.assertEquals(WorkOrderStatus.CLOSED, workOrder.getStatus());
    }
}
