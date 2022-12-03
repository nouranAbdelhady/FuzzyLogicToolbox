import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int fuzzySystemNumber=0;
    public static void CreateInitFrame(){
        JFrame frame = new JFrame("Fuzzy Logic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Fuzzy Logic Toolbox");
        label.setBounds(65,50,300,40);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(label);

        JButton option1=new JButton("Create a new fuzzy system");
        option1.setBounds(90,140,200,30);
        option1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fuzzySystemNumber ++;
                CreateSystemMenu(fuzzySystemNumber);
            }
        });
        frame.add(option1);

        JButton option2=new JButton("Quit");
        option2.setBounds(90,200,200,30);
        option2.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        frame.add(option2);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateSystemMenu(int SystemNo){
        JFrame frame = new JFrame("Fuzzy System: "+SystemNo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,300);

        JLabel SystemName = new JLabel("System Name:");
        SystemName.setBounds(20,40,200,30);
        SystemName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(SystemName);

        JTextField systemName = new JTextField(" ");
        systemName.setBounds(250,40,200,30);
        frame.add(systemName);

        JLabel SystemDescription = new JLabel("System Description:");
        SystemDescription.setBounds(20,80,200,30);
        SystemDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(SystemDescription);

        JTextField systemDescription = new JTextField(" ");
        systemDescription.setBounds(250,80,250,50);
        frame.add(systemDescription);

        JButton submit=new JButton("Submit");
        submit.setBounds(190,200,200,30);
        submit.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CreateMainMenu(fuzzySystemNumber,systemName.getText(),systemDescription.getText());
                frame.setVisible(false);
            }
        });
        frame.add(submit);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateMainMenu(int SystemNo,String systemName,String SystemDescription){
        JFrame frame = new JFrame(SystemNo+"."+systemName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);

        JButton addVariables=new JButton("Add Variable");
        addVariables.setBounds(130,50,150,30);
        frame.add(addVariables);

        JButton addFuzzySets=new JButton("Add Fuzzy Sets");
        addFuzzySets.setBounds(300,50,150,30);
        frame.add(addFuzzySets);

        JButton addRules=new JButton("Add Rules");
        addRules.setBounds(130,150,150,30);
        frame.add(addRules);

        JButton runSimulation=new JButton("Run");
        runSimulation.setBounds(300,150,150,30);
        frame.add(runSimulation);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        CreateInitFrame();

    }
}