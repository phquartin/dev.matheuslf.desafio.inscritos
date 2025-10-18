package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.project.ProjectModel;
import dev.matheuslf.desafio.inscritos.project.ProjectRepository;
import dev.matheuslf.desafio.inscritos.task.dtos.CreateTaskRequest;
import dev.matheuslf.desafio.inscritos.task.dtos.TaskResponse;
import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;
import dev.matheuslf.desafio.inscritos.task.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final ProjectRepository projectRepository;
    private final TaskMapper mapper;
    public TaskService(TaskRepository repository, TaskMapper mapper, ProjectRepository projectRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    public TaskResponse save(CreateTaskRequest request){

        Long projectId = request.projectId();
        ProjectModel projectModel = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found"));

        if(projectModel.getEndDate() != null && projectModel.getEndDate().isBefore(request.dueDate())){
            throw new IllegalArgumentException("Project end date cannot be before task due date");
        }
        if(projectModel.getStartDate().isAfter(request.dueDate())){
            throw new IllegalArgumentException("Project start date cannot be after task due date");
        }

        TaskModel model = mapper.toModel(request);


        if(model.getStatus() == null){
            model.setStatus(Status.TODO);
        }

        model.setProject(projectModel);
        repository.save(model);
        return mapper.toResponse(model);
    }

    public TaskResponse patchStatus(Status status, Long id){
        TaskModel model = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        model.setStatus(status);
        repository.save(model);
        return mapper.toResponse(model);
    }

    public Page<TaskResponse> findAllWithFilter(Pageable pageable, Status status, Priority priority, Long projectId){
        Page<TaskModel> filteredTasks = repository.findByStatusAndPriorityAndProject_Id(status, priority, projectId, pageable);
        return filteredTasks.map(mapper::toResponse);
    }

    public void delete(Long id){
        repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        repository.deleteById(id);
    }

}
