package dev.matheuslf.desafio.inscritos.project.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateProjectRequest(
        @NotBlank(message = "name cannot be blank")
        @Min(value = 3, message = "name must have at least 3 characters")
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
