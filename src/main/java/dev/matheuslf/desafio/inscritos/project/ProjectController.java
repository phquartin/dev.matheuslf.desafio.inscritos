package dev.matheuslf.desafio.inscritos.project;

import dev.matheuslf.desafio.inscritos.project.dtos.CreateProjectRequest;
import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
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

@Tag(name = "Projects", description = "Project Management")
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(
            summary = "Creates a new Project",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Project created.",
                            content = @Content(schema = @Schema(implementation = ProjectResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Problem with request body.")
            }
    )
    @PostMapping()
    public ResponseEntity<ProjectResponse> save(@RequestBody @Valid CreateProjectRequest request){
        ProjectResponse saved = service.save(request);
        return ResponseEntity.status(201).body(saved);
    }
    @Operation(
            summary = "Find all projects with pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Page of with all projects",
                            content = @Content(schema = @Schema(implementation = ProjectResponse.class)))
            }
    )
    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(Pageable pageable){
        return ResponseEntity.status(200).body(service.findAll(pageable));
    }

}
