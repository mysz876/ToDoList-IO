import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JPanel bottomPanel;
    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JButton btnNewTask;
    private JButton btnClearTask;
    private JButton btn;
    private Manager manager = new Manager();

    public MainFrame() {
        setTitle("Converter - IO");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
        btnNewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "placeholder";
                String taskName = JOptionPane.showInputDialog(null, msg, "New Task", JOptionPane.QUESTION_MESSAGE);
                System.out.println(taskName);
                manager.createTask(taskName);
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.saveTasks();
            }
        });
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
