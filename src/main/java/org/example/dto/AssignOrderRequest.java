package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssignOrderRequest(
        @NotBlank @Size(max = 255) String assignedTo) {
}
