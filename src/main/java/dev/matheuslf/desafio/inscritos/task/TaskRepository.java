package dev.matheuslf.desafio.inscritos.task;

import dev.matheuslf.desafio.inscritos.task.model.Priority;
import dev.matheuslf.desafio.inscritos.task.model.Status;
import dev.matheuslf.desafio.inscritos.task.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    @Query("SELECT t FROM TaskModel t WHERE "
            + "(:status IS NULL OR t.status = :status) AND "
            + "(:priority IS NULL OR t.priority = :priority) AND "
            + "(:projectId IS NULL OR t.project.id = :projectId)")
    Page<TaskModel> findTasks(@Param("status") Status status,
                              @Param("priority") Priority priority,
                              @Param("projectId") Long projectId,
                              Pageable pageable);
}
