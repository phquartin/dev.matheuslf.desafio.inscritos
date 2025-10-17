package dev.matheuslf.desafio.inscritos.project.mapper;

import dev.matheuslf.desafio.inscritos.project.ProjectModel;
import dev.matheuslf.desafio.inscritos.project.dtos.CreateProjectRequest;
import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectModel toModel(CreateProjectRequest request) {
        return new ProjectModel(
                null,
                request.name(),
                request.description(),
                request.startDate(),
                request.endDate());
    }

    public ProjectResponse toResponse(ProjectModel model) {
        return new ProjectResponse(
                model.getName(),
                model.getDescription(),
                model.getStartDate(),
                model.getEndDate());
    }

}
