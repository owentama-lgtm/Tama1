import java.time.LocalDateTime;

public class Order {
    private int id;
    private String studentName;
    private String status;
    private LocalDateTime createdAt;

    public Order() {}

    public Order(int id, String studentName, String status, LocalDateTime createdAt) {
        this.id = id;
        this.studentName = studentName;
        this.status = status;
        this.createdAt = createdAt;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (created: %s)", id, studentName, status, createdAt);
    }
}
