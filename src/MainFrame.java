import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {

    private JPanel mainPanel, bottomPanel;
    private JButton btnNewTask, btnClearTask, btnSaveTask;
    private Manager manager = new Manager();

    public MainFrame() {
        setTitle("To Do List - IO");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        GridLayout layout = new GridLayout(0,1);
        layout.setVgap(5);
        bottomPanel.setLayout(layout);
        setVisible(true);
        btnNewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "Wprowadź nazwę nowego zadania:";
                String taskName = JOptionPane.showInputDialog(null, msg, "New Task", JOptionPane.QUESTION_MESSAGE);
                if(taskName==null) {
                    //Wyjście z funckji w przypadku interackji z 'Cancel'
                    return;
                }
                if(taskName.isEmpty()) {
                    String errMsg = "Nazwa zadania nie może byc pusta.";
                    JOptionPane.showMessageDialog(null, errMsg, "New Task Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                manager.createTask(taskName);
                loadTask();
            }
        });
        btnSaveTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.saveTasks();
            }
        });
        btnClearTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "Odświeżenie listy zadań spodowduje utracenie nie zapisanych zmian.\nCzy mimo to chce odświeżyć listę?";
                int value = JOptionPane.showConfirmDialog(null, msg, "", JOptionPane.YES_NO_OPTION);
                if(value == 0) {
                    manager = new Manager();
                    loadTask();
                }
            }
        });
        loadTask();
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    private void loadTask() {
        bottomPanel.removeAll();
        List<Task> tasks = manager.getTasks();
        tasks.forEach(task ->  {
            TaskView taskView = new TaskView(task, this);
            bottomPanel.add(taskView);
        });
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }

    public void btnDeleteClick(Task task) {
        if(manager.deleteTask(task)) {
            loadTask();
        };
    }

    public void btnDoClick(Task task) {
        manager.finishTask(task);
        loadTask();
    }
}
