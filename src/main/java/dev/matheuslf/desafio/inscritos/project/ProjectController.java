package dev.matheuslf.desafio.inscritos.project;

import dev.matheuslf.desafio.inscritos.project.dtos.CreateProjectRequest;
import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ProjectResponse> save(@RequestBody @Valid CreateProjectRequest request){
        ProjectResponse saved = service.save(request);
        return ResponseEntity.status(201).body(saved);
    }
    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(Pageable pageable){
        return ResponseEntity.status(200).body(service.findAll(pageable));
    }

}
