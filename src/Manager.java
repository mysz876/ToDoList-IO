import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Reader {
    private List<Task> tasks;
    public Reader() {
        try {
            File file = new File("tasks.json");
            ObjectMapper mapper = new ObjectMapper();
            this.tasks = mapper.readValue(file, new TypeReference<List<Task>>(){});
//            System.out.println(tasks);
//            tasks.forEach(task -> System.out.println(task.getName()));
            System.out.println(tasks.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
    public void createTask() {
        tasks.add(new Task());
    }
}
