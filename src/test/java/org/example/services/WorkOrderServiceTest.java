package org.example.services;

import org.example.models.WorkOrder;
import org.example.models.WorkOrderStatus;
import org.example.repositories.WorkOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

public class WorkOrderServiceTest {

    @Test
    void shouldCreateAndSaveWorkOrder() {
        // Arrange: preparar el repositorio simulado y el servicio.
        WorkOrderRepository repository = Mockito.mock(WorkOrderRepository.class);
        WorkOrderService workOrderService = new WorkOrderService(repository);

        // Act: crear una orden mediante el servicio.
        WorkOrder workOrder = workOrderService.crearWorkOrder(
                "Test de creación de una orden",
                "Comprueba que el servicio crea una orden en estado OPEN y solicita al repositorio que la guarde."
        );

        // Assert: comprobar el estado y la interacción con el repositorio.
        Assertions.assertEquals(WorkOrderStatus.OPEN, workOrder.getStatus());
        Mockito.verify(repository).save(workOrder);
    }

    @Test
    void shouldFindExistingWorkOrder() {
        // Arrange: preparar el servicio y una orden de prueba.
        WorkOrderRepository repository = Mockito.mock(WorkOrderRepository.class);
        WorkOrderService workOrderService = new WorkOrderService(repository);

        WorkOrder workOrder = new WorkOrder(
                "Test de búsqueda de una orden existente",
                "Comprueba que el servicio devuelve la orden proporcionada por el repositorio simulado al buscar su UUID."
        );

        // Simular que el repositorio encuentra la orden.
        Mockito.when(repository.findById(workOrder.getId()))
                .thenReturn(Optional.of(workOrder));

        // Act: buscar la orden mediante el servicio.
        Optional<WorkOrder> result =
                workOrderService.findWorkOrder(workOrder.getId());

        // Assert: comprobar que el servicio devuelve la orden esperada.
        Assertions.assertEquals(Optional.of(workOrder), result);
    }

    @Test
    void shouldReturnEmptyWhenWorkOrderDoesNotExist() {
        // Arrange: preparar el servicio y un identificador de prueba.
        WorkOrderRepository repository = Mockito.mock(WorkOrderRepository.class);
        WorkOrderService workOrderService = new WorkOrderService(repository);

        UUID id = UUID.randomUUID();

        // Simular que el repositorio no encuentra ninguna orden.
        Mockito.when(repository.findById(id))
                .thenReturn(Optional.empty());

        // Act: buscar una orden inexistente mediante el servicio.
        Optional<WorkOrder> result = workOrderService.findWorkOrder(id);

        // Assert: comprobar que el servicio devuelve un Optional vacío.
        Assertions.assertEquals(Optional.empty(), result);
    }
}