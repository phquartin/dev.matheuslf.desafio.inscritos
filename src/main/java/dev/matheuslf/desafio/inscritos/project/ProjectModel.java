package dev.matheuslf.desafio.inscritos.project;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DATE", name = "start_date")
    private LocalDate startDate;

    @Column(columnDefinition = "DATE", name = "end_date")
    private LocalDate endDate;

    public ProjectModel() {
    }

    public ProjectModel(Long id, String name, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;

        if (startDate == null) {
            startDate = LocalDate.now();
        } else {
            if(startDate.isBefore(LocalDate.now())){
                throw new IllegalArgumentException("start date cannot be before today");
            }
        }
        this.startDate = startDate;

        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
