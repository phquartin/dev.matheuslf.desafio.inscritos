package dev.matheuslf.desafio.inscritos.project.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateProjectRequest(
        @NotBlank(message = "name cannot be blank")
        @Length(min = 3, max = 100, message = "name must have at least 3 characters")
        @Length(max = 100, message = "name cannot have more than 100 characters")
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
