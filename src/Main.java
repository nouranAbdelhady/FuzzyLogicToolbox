import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int fuzzySystemNumber=0;
    static ArrayList<FuzzyLogicSystem> systems = new ArrayList<FuzzyLogicSystem>();
    static Boolean addedVariable=false,addedSet=false,addedRule=false,addedCrisp=false;
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

        JTextField systemName = new JTextField("");
        systemName.setBounds(250,40,200,30);
        frame.add(systemName);

        JLabel SystemDescription = new JLabel("System Description:");
        SystemDescription.setBounds(20,80,200,70);
        SystemDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(SystemDescription);

        JTextField systemDescription = new JTextField("");
        systemDescription.setBounds(250,80,250,90);
        frame.add(systemDescription);

        JButton submit=new JButton("Submit");
        submit.setBounds(190,200,200,30);
        submit.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                FuzzyLogicSystem newSystem = new FuzzyLogicSystem(fuzzySystemNumber,systemName.getText(),systemDescription.getText());
                systems.add(newSystem);
                CreateMainMenu(newSystem);
                frame.setVisible(false);
            }
        });
        frame.add(submit);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateMainMenu(FuzzyLogicSystem thisSystem){
        JFrame frame = new JFrame(thisSystem.id+"."+thisSystem.name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);

        JButton addVariables=new JButton("Add Variable");
        addVariables.setBounds(130,50,150,30);
        frame.add(addVariables);
        addVariables.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CreateVariableFrame(thisSystem);
            }
        });

        JButton addFuzzySets=new JButton("Add Fuzzy Sets");
        addFuzzySets.setBounds(300,50,150,30);
        frame.add(addFuzzySets);
        addFuzzySets.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CreateSetFrame(thisSystem);
            }
        });

        JButton addRules=new JButton("Add Rules");
        addRules.setBounds(130,150,150,30);
        frame.add(addRules);
        addRules.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CreateRuleFrame(thisSystem);
            }
        });

        JButton runSimulation=new JButton("Run");
        runSimulation.setBounds(300,150,150,30);
        frame.add(runSimulation);
        runSimulation.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CreateCrispFrame(thisSystem);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateVariableFrame(FuzzyLogicSystem thisSystem){
        JFrame frame = new JFrame(thisSystem.name+" - Variable");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,300);

        JLabel VariableName = new JLabel("Variable Name:");
        VariableName.setBounds(20,40,200,30);
        VariableName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(VariableName);

        JTextField variableName = new JTextField("");
        variableName.setBounds(250,40,200,30);
        frame.add(variableName);

        JLabel VariableType = new JLabel("Type:");
        VariableType.setBounds(20,100,200,30);
        VariableType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(VariableType);

        ButtonGroup buttonGroup = new ButtonGroup();;
        JRadioButton inType = new JRadioButton();
        inType.setText("IN");
        JRadioButton outType = new JRadioButton();
        outType.setText("OUT");
        buttonGroup.add(inType);
        buttonGroup.add(outType);

        inType.setBounds(230,100,70,30);
        frame.add(inType);
        outType.setBounds(350,100,70,30);
        frame.add(outType);

        JLabel VariableRange = new JLabel("Range:");
        VariableRange.setBounds(20,160,200,30);
        VariableRange.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(VariableRange);

        JSpinner lower = new JSpinner();
        lower.setBounds(230, 160, 50, 40);
        frame.add(lower);

        JSpinner upper = new JSpinner();
        upper.setBounds(350, 160, 50, 40);
        frame.add(upper);

        JButton submit=new JButton("Submit");
        submit.setBounds(230,220,200,30);
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String variableType = "";
                if (inType.isSelected()) {
                    variableType = "IN";
                }
                else if (outType.isSelected()) {
                    variableType = "OUT";
                }
                FuzzyLogicVariable newVariable = new FuzzyLogicVariable(variableName.getText(), variableType,(Integer) lower.getValue(),(Integer) upper.getValue());
                thisSystem.variables.add(newVariable);
                addedVariable=true;
                //System.out.println(thisSystem.toString());
                frame.setVisible(false);
            }
        });
        frame.add(submit);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateSetFrame(FuzzyLogicSystem thisSystem){
        JFrame frame = new JFrame(thisSystem.name+" - Set");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,300);

        JLabel label = new JLabel("Variable:");
        label.setBounds(20,40,200,30);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(label);

        // array of variable names to add to the dropdown list
        String variableNames[] = new String[thisSystem.variables.size()];
        for (int i = 0; i < thisSystem.variables.size(); i++) {
            variableNames[i]=thisSystem.variables.get(i).name;
        }

        // create checkbox
        JComboBox dropdownlist = new JComboBox(variableNames);
        dropdownlist.setBounds(250,40,200,30);
        frame.add(dropdownlist);

        JLabel SetName = new JLabel("Set Name:");
        SetName.setBounds(20,90,200,30);
        SetName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(SetName);

        JTextField setName = new JTextField("");
        setName.setBounds(250,90,200,30);
        frame.add(setName);

        JLabel SetType = new JLabel("Type:");
        SetType.setBounds(20,130,200,30);
        SetType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(SetType);

        ButtonGroup buttonGroup = new ButtonGroup();;
        JRadioButton triType = new JRadioButton();
        triType.setText("TRI");
        JRadioButton trapType = new JRadioButton();
        trapType.setText("TRAP");
        buttonGroup.add(triType);
        buttonGroup.add(trapType);

        triType.setBounds(100,130,70,30);
        frame.add(triType);
        trapType.setBounds(190,130,70,30);
        frame.add(trapType);

        JTextField value1 = new JTextField("");
        value1.setBounds(190,170, 40, 30);
        frame.add(value1);

        JTextField value2 = new JTextField("");
        value2.setBounds(240,170, 40, 30);
        frame.add(value2);

        JTextField value3 = new JTextField("");
        value3.setBounds(290,170, 40, 30);
        frame.add(value3);

        JTextField value4 = new JTextField("");
        value4.setBounds(340,170, 40, 30);
        frame.add(value4);

        JButton submit=new JButton("Submit");
        submit.setBounds(230,220,200,30);
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String setType = "";
                FuzzyLogicSets newSet = null;
                if (triType.isSelected()) {
                    setType = "TRI";
                    //3 values
                    newSet = new FuzzyLogicSets(setName.getText(),setType,
                            new ArrayList<Integer>(Arrays.asList(Integer.parseInt(value1.getText()),Integer.parseInt(value2.getText())
                                    ,Integer.parseInt(value3.getText()))));
                }
                else if (trapType.isSelected()) {
                    setType = "TRAP";
                    //4 values
                    newSet= new FuzzyLogicSets(setName.getText(),setType,
                            new ArrayList<Integer>(Arrays.asList(Integer.parseInt(value1.getText()),Integer.parseInt(value2.getText())
                                    ,Integer.parseInt(value3.getText()),Integer.parseInt(value4.getText()))));
                }

                for (int i = 0; i < thisSystem.variables.size(); i++) {
                    if(thisSystem.variables.get(i).name.equals(dropdownlist.getSelectedItem().toString())){
                        thisSystem.variables.get(i).sets.add(newSet);
                    }
                }
                addedSet=true;
                //System.out.println(thisSystem.toString());
                frame.setVisible(false);
            }
        });
        frame.add(submit);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateRuleFrame(FuzzyLogicSystem thisSystem){
        JFrame frame = new JFrame(thisSystem.name+" - Rule");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,700);

        JLabel RuleName = new JLabel("Rule Name:");
        RuleName.setBounds(20,10,200,30);
        RuleName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(RuleName);
        JTextField ruleName = new JTextField("");
        ruleName.setBounds(200,10,250,30);
        frame.add(ruleName);

        //1ST TERM
        // array of variable names to add to the dropdown list
        String variableNamesInput[] = new String[thisSystem.variables.size()];
        String variableNamesOutput[] = new String[thisSystem.variables.size()];
        ArrayList<String> setsNamesInputArrayList = new ArrayList<String>();
        ArrayList<String> setsNamesOutputArrayList = new ArrayList<String>();
        int inCount=0,outCount=0;
        int inSetCount=0, outSetCount=0;
        for (int i = 0; i < thisSystem.variables.size(); i++) {
            if(thisSystem.variables.get(i).type.equals("IN")){
                variableNamesInput[inCount]=thisSystem.variables.get(i).name;
                for (FuzzyLogicSets set: thisSystem.variables.get(i).sets ) {
                    setsNamesInputArrayList.add(set.name);
                    inSetCount++;
                }
                inCount++;
            }
            else{
                variableNamesOutput[outCount]=thisSystem.variables.get(i).name;
                for (FuzzyLogicSets set: thisSystem.variables.get(i).sets ) {
                    setsNamesOutputArrayList.add(set.name);
                    outSetCount++;
                }
                outCount++;
            }
        }

        String[] setsNamesInput = new String[inSetCount];
        String[] setsNamesOutput = new String[outSetCount];
        for (int i = 0; i < inSetCount; i++) {
            setsNamesInput[i]=setsNamesInputArrayList.get(i);
        }
        for (int i = 0; i < outSetCount; i++) {
            setsNamesOutput[i]=setsNamesOutputArrayList.get(i);
        }

        JComboBox variableList1 = new JComboBox(variableNamesInput);
        variableList1.setBounds(20,80,200,30);
        frame.add(variableList1);

        JComboBox variableSet1 = new JComboBox(setsNamesInput);
        variableSet1.setBounds(270,80,200,30);
        frame.add(variableSet1);

        ButtonGroup buttonGroupNot1 = new ButtonGroup();;
        JRadioButton NOT1 = new JRadioButton();
        NOT1.setText("NOT");
        buttonGroupNot1.add(NOT1);
        NOT1.setBounds(500,80,70,30);
        frame.add(NOT1);

        ButtonGroup buttonGroupOperator1 = new ButtonGroup();;
        JRadioButton OR1 = new JRadioButton();
        OR1.setText("OR");
        JRadioButton AND1 = new JRadioButton();
        AND1.setText("AND");
        buttonGroupOperator1.add(OR1);
        buttonGroupOperator1.add(AND1);
        OR1.setBounds(200,130,70,30);
        frame.add(OR1);
        AND1.setBounds(290,130,70,30);
        frame.add(AND1);

        //2ND TERM
        JComboBox variableList2 = new JComboBox(variableNamesInput);
        variableList2.setBounds(20,180,200,30);
        frame.add(variableList2);

        JComboBox variableSet2 = new JComboBox(setsNamesInput);
        variableSet2.setBounds(270,180,200,30);
        frame.add(variableSet2);

        ButtonGroup buttonGroupNot2 = new ButtonGroup();;
        JRadioButton NOT2 = new JRadioButton();
        NOT2.setText("NOT");
        buttonGroupNot2.add(NOT2);
        NOT2.setBounds(500,180,70,30);
        frame.add(NOT2);

        //OUTPUT TERM
        JLabel dashedLine = new JLabel("--------------------------------------------------------------------");
        dashedLine.setBounds(200,280,200,30);
        dashedLine.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(dashedLine);

        JComboBox outputList = new JComboBox(variableNamesOutput);
        outputList.setBounds(50,400,200,30);
        frame.add(outputList);

        JComboBox outputSet = new JComboBox(setsNamesOutput);
        outputSet.setBounds(300,400,200,30);
        frame.add(outputSet);

        JButton submit=new JButton("Submit");
        submit.setBounds(350,500,200,30);
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                FuzzyLogicVariable variable1=null;
                FuzzyLogicVariable variable2=null;
                FuzzyLogicVariable outputVariable=null;
                //get variables from name
                for (int i = 0; i < thisSystem.variables.size(); i++) {
                    if(thisSystem.variables.get(i).name.equals(variableList1.getSelectedItem().toString())){
                       variable1= thisSystem.variables.get(i);
                    }
                    if(thisSystem.variables.get(i).name.equals(variableList2.getSelectedItem().toString())){
                        variable2= thisSystem.variables.get(i);
                    }
                    if(thisSystem.variables.get(i).name.equals(outputList.getSelectedItem().toString())){
                        outputVariable= thisSystem.variables.get(i);
                    }
                }
                FuzzyLogicRule.InputRule subrule1 = new FuzzyLogicRule.InputRule(variable1,variableSet1.getSelectedItem().toString(),NOT1.isSelected());
                FuzzyLogicRule.InputRule subrule2 = new FuzzyLogicRule.InputRule(variable2,variableSet2.getSelectedItem().toString(),NOT2.isSelected());

                String operatorType = "";
                if (AND1.isSelected()) {
                    operatorType = "AND";
                }
                else if (OR1.isSelected()) {
                    operatorType = "OR";
                }

                FuzzyLogicRule newRule = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule1,subrule2)),new ArrayList(Arrays.asList(operatorType)),outputVariable,outputSet.getSelectedItem().toString());
                thisSystem.rules.add(newRule);
                //System.out.println(thisSystem.toString());
                addedRule=true;
                frame.setVisible(false);
            }
        });
        frame.add(submit);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateCrispFrame(FuzzyLogicSystem thisSystem){
        JFrame frame = new JFrame(thisSystem.name+" - Crisp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,300);

        JLabel VariableName = new JLabel("Variable:");
        VariableName.setBounds(20,40,200,30);
        VariableName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(VariableName);

        // array of variable names to add to the dropdown list
        String variableNamesInput[] = new String[thisSystem.variables.size()];
        int inCount=0,outCount=0;
        for (int i = 0; i < thisSystem.variables.size(); i++) {
            if(thisSystem.variables.get(i).type.equals("IN")){
                variableNamesInput[inCount]=thisSystem.variables.get(i).name;
                inCount++;
            }
        }
        JComboBox variableList1 = new JComboBox(variableNamesInput);
        variableList1.setBounds(180,40,200,30);
        frame.add(variableList1);


        JLabel OutputValue = new JLabel("Crisp Value:");
        OutputValue.setBounds(20,100,200,30);
        OutputValue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.add(OutputValue);

        JTextField outputValue = new JTextField("");
        outputValue.setBounds(180,100,200,30);
        frame.add(outputValue);

        JButton runButton=new JButton("Run");
        runButton.setBounds(230,220,150,30);
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!outputValue.getText().equals("")){
                    FuzzyLogicVariable cripsVariable=null;

                    //get variables from name
                    for (int i = 0; i < thisSystem.variables.size(); i++) {
                        if(thisSystem.variables.get(i).name.equals(variableList1.getSelectedItem().toString())){
                            cripsVariable= thisSystem.variables.get(i);
                        }
                    }
                    FuzzyLogicCrispValues newCrisp = new FuzzyLogicCrispValues(cripsVariable,Integer.parseInt(outputValue.getText()));
                    thisSystem.crispValues.add(newCrisp);
                    addedCrisp=true;
                    //System.out.println(thisSystem.toString());
                }
                if(addedVariable&&addedSet&&addedRule&&addedCrisp){
                    CreateRunFrame(thisSystem);
                }
                else{
                    CreateErrorFrame();
                }
                frame.setVisible(false);
            }
        });
        frame.add(runButton);

        JButton addAnotherCrisp=new JButton("Add");
        addAnotherCrisp.setBounds(400,220,150,30);
        addAnotherCrisp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!outputValue.getText().equals("")){
                    FuzzyLogicVariable cripsVariable=null;

                    //get variables from name
                    for (int i = 0; i < thisSystem.variables.size(); i++) {
                        if(thisSystem.variables.get(i).name.equals(variableList1.getSelectedItem().toString())){
                            cripsVariable= thisSystem.variables.get(i);
                        }
                    }
                    FuzzyLogicCrispValues newCrisp = new FuzzyLogicCrispValues(cripsVariable,Integer.parseInt(outputValue.getText()));
                    thisSystem.crispValues.add(newCrisp);
                    addedCrisp=true;
                    //System.out.println(thisSystem.toString());
                }
                CreateCrispFrame(thisSystem);
                frame.setVisible(false);
            }
        });
        frame.add(addAnotherCrisp);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateRunFrame(FuzzyLogicSystem thisSystem){
        JFrame frame = new JFrame(thisSystem.name+" - Run");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450,350);

        String outputMessage = thisSystem.Run();
        System.out.println("OUTPUT MESSAGE");
        System.out.println(outputMessage);

        JLabel OutputMessage = new JLabel();
        OutputMessage.setText("<html>" + outputMessage.replaceAll("\n", "<br/>") + "</html>");

        OutputMessage.setBounds(100,35,250,260);
        OutputMessage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        frame.add(OutputMessage);

        //System.out.println("Final System State: ");
        //System.out.println(thisSystem.toString());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void CreateErrorFrame(){
        JFrame frame = new JFrame("Error!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,200);

        String errorMessage="CAN’T START THE SIMULATION!";
        JLabel ErrorMessage = new JLabel(errorMessage);
        ErrorMessage.setBounds(70,40,250,50);
        ErrorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        frame.add(ErrorMessage);

        String errorMessage2="Please add missing requirements ";
        ErrorMessage = new JLabel(errorMessage2);
        ErrorMessage.setBounds(85,70,250,50);
        ErrorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        frame.add(ErrorMessage);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        CreateInitFrame();

        /*
        FuzzyLogicSets set1= new FuzzyLogicSets("very_low","TRAP",new ArrayList<Integer>(Arrays.asList(0,0,10,30)));
        FuzzyLogicSets set2= new FuzzyLogicSets("low","TRAP",new ArrayList<Integer>(Arrays.asList(10,30,40,60)));
        FuzzyLogicSets set3= new FuzzyLogicSets("medium","TRAP",new ArrayList<Integer>(Arrays.asList(40,60,70,90)));
        FuzzyLogicSets set4= new FuzzyLogicSets("high","TRAP",new ArrayList<Integer>(Arrays.asList(70,90,100,100)));
        ArrayList<FuzzyLogicSets> sets = new ArrayList<FuzzyLogicSets>(Arrays.asList(set1,set2,set3,set4));
        FuzzyLogicVariable variable1 = new FuzzyLogicVariable("proj_funding","IN",0,100);
        variable1.sets=sets;
        FuzzyLogicCrispValues crips1 = new FuzzyLogicCrispValues(variable1,50);

        FuzzyLogicSystem system1 = new FuzzyLogicSystem(1,"System 1","Des 1");
        system1.variables.add(variable1);
        system1.crispValues.add(crips1);

        FuzzyLogicSets set11= new FuzzyLogicSets("low","TRI",new ArrayList<Integer>(Arrays.asList(0,25,50)));
        FuzzyLogicSets set22= new FuzzyLogicSets("normal","TRI",new ArrayList<Integer>(Arrays.asList(25,50,75)));
        FuzzyLogicSets set33= new FuzzyLogicSets("high","TRI",new ArrayList<Integer>(Arrays.asList(50,100,100)));
        ArrayList<FuzzyLogicSets> sets1 = new ArrayList<FuzzyLogicSets>(Arrays.asList(set11,set22,set33));
        FuzzyLogicVariable variable2 = new FuzzyLogicVariable("risk","OUT",0,100);
        variable2.sets=sets1;
        system1.variables.add(variable2);

        FuzzyLogicSets set12= new FuzzyLogicSets("beginner","TRI",new ArrayList<Integer>(Arrays.asList(0,15,30)));
        FuzzyLogicSets set23= new FuzzyLogicSets("intermediate","TRI",new ArrayList<Integer>(Arrays.asList(15,30,45)));
        FuzzyLogicSets set34= new FuzzyLogicSets("expert","TRI",new ArrayList<Integer>(Arrays.asList(30,60,60)));
        ArrayList<FuzzyLogicSets> sets2 = new ArrayList<FuzzyLogicSets>(Arrays.asList(set12,set23,set34));
        FuzzyLogicVariable variable3 = new FuzzyLogicVariable("exp_level","IN",0,60);
        variable3.sets=sets2;
        system1.variables.add(variable3);
        FuzzyLogicCrispValues crips2 = new FuzzyLogicCrispValues(variable3,40);
        system1.crispValues.add(crips2);

        FuzzyLogicRule.InputRule subrule11 = new FuzzyLogicRule.InputRule(variable1,"high",false);
        FuzzyLogicRule.InputRule subrule21 = new FuzzyLogicRule.InputRule(variable3,"expert",false);
        FuzzyLogicRule rule1 = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule11,subrule21)),new ArrayList(Arrays.asList("OR")),variable2,"low");

        FuzzyLogicRule.InputRule subrule12 = new FuzzyLogicRule.InputRule(variable1,"medium",false);
        FuzzyLogicRule.InputRule subrule22 = new FuzzyLogicRule.InputRule(variable3,"intermediate",false);
        FuzzyLogicRule rule2 = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule12,subrule22)),new ArrayList(Arrays.asList("AND")),variable2,"normal");

        FuzzyLogicRule.InputRule subrule13 = new FuzzyLogicRule.InputRule(variable1,"medium",false);
        FuzzyLogicRule.InputRule subrule23 = new FuzzyLogicRule.InputRule(variable3,"beginner",false);
        FuzzyLogicRule rule3 = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule13,subrule23)),new ArrayList(Arrays.asList("AND")),variable2,"normal");

        FuzzyLogicRule.InputRule subrule14 = new FuzzyLogicRule.InputRule(variable1,"low",false);
        FuzzyLogicRule.InputRule subrule24 = new FuzzyLogicRule.InputRule(variable3,"beginner",false);
        FuzzyLogicRule rule4 = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule14,subrule24)),new ArrayList(Arrays.asList("AND")),variable2,"high");

        FuzzyLogicRule.InputRule subrule15 = new FuzzyLogicRule.InputRule(variable1,"very_low",false);
        FuzzyLogicRule.InputRule subrule25 = new FuzzyLogicRule.InputRule(variable3,"expert",true);
        FuzzyLogicRule rule5 = new FuzzyLogicRule(new ArrayList(Arrays.asList(subrule15,subrule25)),new ArrayList(Arrays.asList("AND")),variable2,"high");

        system1.rules.add(rule1);
        system1.rules.add(rule2);
        system1.rules.add(rule3);
        system1.rules.add(rule4);
        system1.rules.add(rule5);
        systems.add(system1);
        system1.Fuzzification();
        system1.Inference();
        System.out.println(system1.Defuzzification());

        //CreateRunFrame(system1);
         */
    }
}