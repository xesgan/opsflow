package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class WorkOrder {

    private String title;
    private String description;
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;
    private LocalDateTime createdAt;
    private String assignedTo;
//    There's a little limitation because it need to be thinked. Or probably
//    not to many properties needed. Ex: userName, password...
//    private User createdBy;

    protected WorkOrder() {
    }

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
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
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

    public String getAssignedTo() {
        return assignedTo;
    }

    public void assign(String assignedTo) {
        if (status != WorkOrderStatus.OPEN) {
            throw new IllegalStateException("Solo se pueden asignar órdenes abiertas");
        }
        if (assignedTo == null || assignedTo.isBlank()) {
            throw new IllegalArgumentException("El responsable es obligatorio");
        }
        if (getStatus() == WorkOrderStatus.OPEN) {
            this.assignedTo = assignedTo;
            this.status = WorkOrderStatus.ASSIGNED;
        }
    }

    public void inProgress() {
        if (status != WorkOrderStatus.ASSIGNED) {
            throw new IllegalStateException("Solo se pueden poner en marcha las órdenes que estén asignadas");
        }
        if (assignedTo.isBlank() || assignedTo.isEmpty()) {
            throw new IllegalArgumentException("El responsable es obligatorio");
        }
        this.status = WorkOrderStatus.IN_PROGRESS;
    }

    public void resolve() {
        if (status != WorkOrderStatus.IN_PROGRESS) {
            throw new IllegalStateException("Solo se pueden resolver las órdenes que estén empezadas");
        }
        this.status = WorkOrderStatus.RESOLVED;
    }

    public void close() {
        if (status != WorkOrderStatus.RESOLVED) {
            throw new IllegalStateException("Solo se pueden cerrar las órdenes resueltas");
        }
        this.status = WorkOrderStatus.CLOSED;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", assignedTo=" + assignedTo +
                '}';
    }


    public void cancel() {
        if (status != WorkOrderStatus.OPEN && status != WorkOrderStatus.ASSIGNED && status != WorkOrderStatus.IN_PROGRESS) {
            throw new IllegalStateException("Solo se pueden cancelar ordenes abiertas, asignadas o en progreso");
        }
        this.status = WorkOrderStatus.CANCELLED;
    }

    public void reopen() {
        if(status != WorkOrderStatus.RESOLVED){
            throw new IllegalStateException("Solo se pueden reabrir las ordenes resueltas");
        }
        this.status = WorkOrderStatus.IN_PROGRESS;
    }
}
