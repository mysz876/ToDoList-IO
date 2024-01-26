import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private String name;
    private boolean status;
    public Task() { }
    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    public String getName() {
        return this.name;
    }
    public boolean getStatus() {
        return this.status;
    }
    public void finishTask() {
        this.status = true;
    }
}
