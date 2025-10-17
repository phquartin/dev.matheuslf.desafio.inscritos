package dev.matheuslf.desafio.inscritos.project;

import dev.matheuslf.desafio.inscritos.project.dtos.CreateProjectRequest;
import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
import dev.matheuslf.desafio.inscritos.project.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    public ProjectService(ProjectRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProjectResponse save(CreateProjectRequest request){
        ProjectModel model = mapper.toModel(request);
        repository.save(model);
        return mapper.toResponse(model);
    }

}
