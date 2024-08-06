package org.springframework.TaskManagement.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Task extends BaseEntity {

    @NotBlank(message = "The title must be not blank")
    private String title;

    private String description;

    private String status;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

}
