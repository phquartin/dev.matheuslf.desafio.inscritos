package dev.matheuslf.desafio.inscritos.project;

import dev.matheuslf.desafio.inscritos.project.dtos.CreateProjectRequest;
import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectModel toModel(CreateProjectRequest request);

    ProjectResponse toResponse(ProjectModel model);

}
