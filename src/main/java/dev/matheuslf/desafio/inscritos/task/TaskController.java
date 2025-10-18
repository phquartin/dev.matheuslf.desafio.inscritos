package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.task.dtos.CreateTaskRequest;
import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> save(@RequestBody @Valid CreateTaskRequest request){
        TaskResponse saved = service.save(request);
        return ResponseEntity.status(201).body(saved);
    }

}
