import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Manager {
    private final List<Task> tasks;
    public Manager() {
        try {
            File file = new File("tasks.json");
            ObjectMapper mapper = new ObjectMapper();
            this.tasks = mapper.readValue(file, new TypeReference<List<Task>>(){});
            System.out.println(tasks.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
    public void createTask(String taskName) {
        int id = tasks.size() + 1;
        tasks.add(new Task(tasks.size()+1,taskName,false));
        System.out.println(tasks.size());
    }

    public void saveTasks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(this.tasks));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
