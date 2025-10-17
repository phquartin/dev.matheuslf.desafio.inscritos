package dev.matheuslf.desafio.inscritos.project.dtos;

import java.time.LocalDate;

public record ProjectResponse(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
