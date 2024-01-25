import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private int id;
    private String name;
    private boolean status;
    public Task() { }
    public Task(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public boolean getStatus() {
        return this.status;
    }
}
