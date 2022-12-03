import java.util.ArrayList;

public class FuzzyLogicSystem {
    String name;
    String description;
    ArrayList<FuzzyLogicVariable> variables = new ArrayList<FuzzyLogicVariable>();
    ArrayList<FuzzyLogicRule> rules = new ArrayList<FuzzyLogicRule>();
    ArrayList<FuzzyLogicCrispValues> crispValues = new ArrayList<FuzzyLogicCrispValues>();

    public FuzzyLogicSystem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void Fuzzification(){
        //Loop on each Crisp Value
        for (FuzzyLogicCrispValues crisp : crispValues) {
            FuzzyLogicVariable crispVariable = crisp.variable;
            //Loop on sets for the crisp values and see which sets it breaks
            for (int i = 0; i < crispVariable.sets.size(); i++) {
                System.out.println("Set: "+i+1+" - "+crispVariable.sets.get(i).name);
                //check if it is in range (>=first and <=last)
                if(crisp.value >= crispVariable.sets.get(i).values.get(0)
                    && crisp.value <= crispVariable.sets.get(i).values.get(-1)){
                    System.out.println("Inside range");
                    //Calculate membership degree
                    crisp.membershipDegree.add(1.0);
                    crisp.sets_name.add(crispVariable.sets.get(i).name);
                }
                //not in range => membership degree=0
                else{
                    System.out.println("Outside range");
                    crisp.membershipDegree.add(0.0);
                    crisp.sets_name.add(crispVariable.sets.get(i).name);
                }
            }
        }
    }
    public void Inference(){

    }
    public void Defuzzification(){

    }

    public void Run(){
        Fuzzification();
        Inference();
        Defuzzification();
    }

}
