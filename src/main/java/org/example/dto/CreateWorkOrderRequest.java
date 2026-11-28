package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWorkOrderRequest(
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 255) String description) {

}
