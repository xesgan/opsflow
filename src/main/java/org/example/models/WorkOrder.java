package org.example.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class WorkOrder {

    private String title;
    private String description;
    private UUID id;
    private WorkOrderStatus status;
    private LocalDateTime createdAt;
//    There's a little limitation because it need to be thinked. Or probably
//    not to many properties needed. Ex: userName, password...
//    private User createdBy;

    public WorkOrder(String title, String description) {
        setTitle(title);
        setDescription(description);
        // Y mas adelante lo modificariamos para que lo genere PostgreSQL al persistirla
        this.id = UUID.randomUUID();
        this.status = WorkOrderStatus.OPEN;
        this.createdAt = LocalDateTime.now();
//        this.createdBy = createdBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public WorkOrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
