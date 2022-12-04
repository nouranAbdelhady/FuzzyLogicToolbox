import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int fuzzySystemNumber=0;
    static ArrayList<FuzzyLogicSystem> systems = new ArrayList<FuzzyLogicSystem>();
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
        //CreateInitFrame();

        FuzzyLogicSets set1= new FuzzyLogicSets("very_low","TRAP",new ArrayList<Integer>(Arrays.asList(0,0,10,30)));
        FuzzyLogicSets set2= new FuzzyLogicSets("low","TRAP",new ArrayList<Integer>(Arrays.asList(10,30,40,60)));
        FuzzyLogicSets set3= new FuzzyLogicSets("medium","TRAP",new ArrayList<Integer>(Arrays.asList(40,60,70,90)));
        FuzzyLogicSets set4= new FuzzyLogicSets("high","TRAP",new ArrayList<Integer>(Arrays.asList(70,90,100,100)));
        ArrayList<FuzzyLogicSets> sets = new ArrayList<FuzzyLogicSets>(Arrays.asList(set1,set2,set3,set4));
        FuzzyLogicVariable variable1 = new FuzzyLogicVariable("proj_funding","IN",0,100,sets);
        FuzzyLogicCrispValues crips1 = new FuzzyLogicCrispValues(variable1,50);

        FuzzyLogicSystem system1 = new FuzzyLogicSystem("System 1","Des 1");
        system1.variables.add(variable1);
        system1.crispValues.add(crips1);

        FuzzyLogicSets set11= new FuzzyLogicSets("low","TRI",new ArrayList<Integer>(Arrays.asList(0,25,50)));
        FuzzyLogicSets set22= new FuzzyLogicSets("normal","TRI",new ArrayList<Integer>(Arrays.asList(25,50,75)));
        FuzzyLogicSets set33= new FuzzyLogicSets("high","TRI",new ArrayList<Integer>(Arrays.asList(50,100,100)));
        ArrayList<FuzzyLogicSets> sets1 = new ArrayList<FuzzyLogicSets>(Arrays.asList(set11,set22,set33));
        FuzzyLogicVariable variable2 = new FuzzyLogicVariable("risk","OUT",0,100,sets1);
        system1.variables.add(variable2);

        FuzzyLogicSets set12= new FuzzyLogicSets("beginner","TRI",new ArrayList<Integer>(Arrays.asList(0,15,30)));
        FuzzyLogicSets set23= new FuzzyLogicSets("intermediate","TRI",new ArrayList<Integer>(Arrays.asList(15,30,45)));
        FuzzyLogicSets set34= new FuzzyLogicSets("expert","TRI",new ArrayList<Integer>(Arrays.asList(30,60,60)));
        ArrayList<FuzzyLogicSets> sets2 = new ArrayList<FuzzyLogicSets>(Arrays.asList(set12,set23,set34));
        FuzzyLogicVariable variable3 = new FuzzyLogicVariable("exp_level","IN",0,60,sets2);
        system1.variables.add(variable3);
        FuzzyLogicCrispValues crips2 = new FuzzyLogicCrispValues(variable3,40);
        system1.crispValues.add(crips2);

        FuzzyLogicRule.InputRule subrule1 = new FuzzyLogicRule.InputRule(variable1,"high",false);
        FuzzyLogicRule.InputRule subrule2 = new FuzzyLogicRule.InputRule(variable3,"expert",false);

        FuzzyLogicRule.InputRule subrule3 = new FuzzyLogicRule.InputRule(variable1,"medium",false);
        FuzzyLogicRule.InputRule subrule4 = new FuzzyLogicRule.InputRule(variable3,"beginner",true);
        FuzzyLogicRule rule1 = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule1,subrule2,subrule3,subrule4)),new ArrayList(Arrays.asList("OR","AND")),variable2,"low");
        system1.rules.add(rule1);
        systems.add(system1);
        system1.Fuzzification();
        system1.Inference();
    }
}