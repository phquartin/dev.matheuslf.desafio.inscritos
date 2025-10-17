package dev.matheuslf.desafio.inscritos.project.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateProjectRequest(
        @NotBlank(message = "name cannot be blank")
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
