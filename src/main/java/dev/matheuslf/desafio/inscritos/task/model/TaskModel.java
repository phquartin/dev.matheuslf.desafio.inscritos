package dev.matheuslf.desafio.inscritos.task.model;

import dev.matheuslf.desafio.inscritos.project.ProjectModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "task")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(columnDefinition = "DATE", name = "due_date")
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectModel project;

}
