package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;
import dev.matheuslf.desafio.inscritos.task.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    Page<TaskModel> findByStatusAndPriorityAndProject_Id(Status status, Priority priority, Long project_id, Pageable pageable);
}
