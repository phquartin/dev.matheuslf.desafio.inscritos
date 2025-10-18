package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.task.dtos.CreateTaskRequest;
import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;
import dev.matheuslf.desafio.inscritos.task.model.TaskModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskModel toModel(CreateTaskRequest request);

    TaskResponse toResponse(TaskModel model);

}
