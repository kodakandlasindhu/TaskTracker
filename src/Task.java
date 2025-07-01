import java.time.LocalDateTime;

public class Task{
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id, String description){
        this.id=id;
        this.description=description;
        this.status="todo";
        this.createdAt= LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Description: " + description + "\n" +
                "Status: " + status + "\n" +
                "Created At: " + createdAt + "\n" +
                "Updated At: " + updatedAt + "\n";
    }
}
