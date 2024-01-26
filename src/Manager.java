import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Task> tasks;
    private final String file = "tasks.json";
    public Manager() {
        try {
            File file = new File(this.file);
            ObjectMapper mapper = new ObjectMapper();
            this.tasks = mapper.readValue(file, new TypeReference<List<Task>>(){});
        } catch (IOException e) {
            String msg = "Wystąpił problem podczas odczyty listy zadań z pliku. Zainicjowano pustą listę.";
            JOptionPane.showMessageDialog(null, msg, "Oped File - Error", JOptionPane.WARNING_MESSAGE);
            this.tasks = new ArrayList<>();
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
    public void createTask(String taskName) {
        int id = tasks.size() + 1;
        tasks.add(new Task(taskName,false));
    }

    public void saveTasks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file));
            bufferedWriter.write(mapper.writeValueAsString(this.tasks));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteTask(Task task) {
        boolean status = this.tasks.remove(task);
        if(status) {
            String msg = String.format("Pomyślnie usunięto zadanie: %s", task.getName());
            JOptionPane.showMessageDialog(null, msg, "Delete task information", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            String msg = String.format("Nie udało się usunąć zadania: %s", task.getName());
            JOptionPane.showMessageDialog(null, msg, "Delete task information", JOptionPane.INFORMATION_MESSAGE);
        }
        return status;
    }

    public void finishTask(Task task) {
        this.tasks.get(this.tasks.indexOf(task)).finishTask();
    }
}
