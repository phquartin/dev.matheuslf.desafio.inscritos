package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
import dev.matheuslf.desafio.inscritos.task.dtos.CreateTaskRequest;
import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;
import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Tasks", description = "Tasks Management")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(
            summary = "Creates a new Task",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Task created.",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Problem with request body.")
            }
    )
    @PostMapping()
    public ResponseEntity<TaskResponse> save(@RequestBody @Valid CreateTaskRequest request){
        TaskResponse saved = service.save(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping()
    public ResponseEntity<Page<TaskResponse>> findAll(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = true) Long projectId,
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
