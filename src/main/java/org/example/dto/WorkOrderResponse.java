package org.example.dto;

import org.example.models.WorkOrder;
import org.example.models.WorkOrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record WorkOrderResponse(
        UUID id,
        String title,
        String description,
        WorkOrderStatus status,
        LocalDateTime createdAt,
        String assignedTo
) {}
