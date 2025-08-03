package model;

import service.TaskStatus;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected TaskStatus status;

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object t) {
        if (this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        Task task = (Task) t;
        return id == task.id;
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}