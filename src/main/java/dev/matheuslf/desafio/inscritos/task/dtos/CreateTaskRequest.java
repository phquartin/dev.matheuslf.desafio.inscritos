package dev.matheuslf.desafio.inscritos.task.dtos;

import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateTaskRequest(

        @NotBlank(message = "title cannot be blank")
        @Length(min = 5, message = "title must have at least 5 characters")
        @Length(max = 150, message = "title cannot have more than 150 characters")
        String title,
        String description,
        @NotNull(message = "status cannot be NULL")
        Status status,
        @NotNull(message = "priority cannot be NULL")
        Priority priority,
        LocalDate dueDate,

        @NotNull(message = "projectId cannot be NULL")
        Long projectId



) {
}
