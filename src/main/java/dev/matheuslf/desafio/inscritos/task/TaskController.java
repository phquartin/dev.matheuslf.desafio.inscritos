package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.task.dtos.CreateTaskRequest;
import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;
import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<Page<TaskResponse>> findAll(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Long projectId,
            Pageable pageable)
    {
        Page<TaskResponse> allWithFilter = service.findAllWithFilter(pageable, status, priority, projectId);
        return ResponseEntity.status(200).body(allWithFilter);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> patchStatus(@PathVariable Long id, @RequestBody Status status){
        TaskResponse taskResponse = service.patchStatus(status, id);
        return ResponseEntity.status(200).body(taskResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(204).build();
    }

}
