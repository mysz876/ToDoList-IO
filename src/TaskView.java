import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskView extends JPanel {
    private MainFrame mainFrame;
    private JPanel btnPanel, namePanel, statusPanel;
    private JButton delBtn, doBtn;
    private JLabel nameTask;
    private Task task;

    public TaskView(Task task, MainFrame frame) {
        this.mainFrame = frame;
        this.task = task;
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout());

        statusPanel = new JPanel();
        if(task.getStatus()) {
            statusPanel.setBackground(Color.decode("#93D393"));
        }
        else {
            statusPanel.setBackground(Color.decode("#E27B80"));
        }
        statusPanel.setPreferredSize(new Dimension(20,0));
        add(statusPanel, BorderLayout.WEST);

        namePanel = new JPanel();
        namePanel.setLayout(new CardLayout());
        namePanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        nameTask = new JLabel(task.getName());
        namePanel.add(nameTask);
        add(namePanel, BorderLayout.CENTER);


        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(0,1));
        delBtn = new JButton("Usuń zadanie");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.btnDeleteClick(task);
            }
        });
        btnPanel.add(delBtn);
        if(!task.getStatus()) {
            doBtn = new JButton("Wykonaj zadanie");
            doBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.btnDoClick(task);
                }
            });
            btnPanel.add(doBtn);
        }
        add(btnPanel, BorderLayout.EAST);
    }
}
