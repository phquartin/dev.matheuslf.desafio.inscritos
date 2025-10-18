package dev.matheuslf.desafio.inscritos.project;

import dev.matheuslf.desafio.inscritos.project.dtos.CreateProjectRequest;
import dev.matheuslf.desafio.inscritos.project.dtos.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        if(model.getStartDate() == null ){
            model.setStartDate(LocalDate.now());
        } else if (model.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("startDate cannot be before today");
        } else if (model.getEndDate() != null && model.getEndDate().isBefore(model.getStartDate())) {
            throw new IllegalArgumentException("endDate cannot be before startDate");
        }

        repository.save(model);
        return mapper.toResponse(model);
    }

    public Page<ProjectResponse> findAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponse);
    }

}
