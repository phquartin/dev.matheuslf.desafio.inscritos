package dev.matheuslf.desafio.inscritos.task.dtos;

import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;

import java.time.LocalDate;

public record TaskResponse(
    Long id,
    String title,
    String description,
    Status status,
    Priority priority,
    LocalDate dueDate,
    String projectName
) {
}
