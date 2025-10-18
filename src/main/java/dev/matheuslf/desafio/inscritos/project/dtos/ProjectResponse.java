package dev.matheuslf.desafio.inscritos.project.dtos;

import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;

import java.time.LocalDate;
import java.util.List;

public record ProjectResponse(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        List<TaskResponse> tasks
) {
}
