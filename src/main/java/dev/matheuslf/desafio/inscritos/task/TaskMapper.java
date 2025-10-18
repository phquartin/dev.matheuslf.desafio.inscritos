package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.task.dtos.CreateTaskRequest;
import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;
import dev.matheuslf.desafio.inscritos.task.model.TaskModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source="status", target="status", defaultValue="TODO")
    TaskModel toModel(CreateTaskRequest request);

    @Mapping(source="description", target="description", defaultValue ="no description")
    TaskResponse toResponse(TaskModel model);

}
