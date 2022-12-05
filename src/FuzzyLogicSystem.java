import java.util.ArrayList;

public class FuzzyLogicSystem {
    String name;
    String description;
    ArrayList<FuzzyLogicVariable> variables = new ArrayList<FuzzyLogicVariable>();
    ArrayList<FuzzyLogicRule> rules = new ArrayList<FuzzyLogicRule>();
    ArrayList<FuzzyLogicCrispValues> crispValues = new ArrayList<FuzzyLogicCrispValues>();

    double calulatedOutputValue;
    String outputSet;

    public FuzzyLogicSystem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "FuzzyLogicSystem{" +
                "name='" + name + '\n' +
                ", description='" + description + '\n' +
                ", variables=" + variables + '\n' +
                ", rules=" + rules + '\n' +
                ", crispValues=" + crispValues + '\n' +
                '}'+ '\n' +'\n';
    }

    public double getMembershipDegreeForCrisp(String targetedVariableName, String targetedSetName){
        for (FuzzyLogicCrispValues crisp : crispValues) {
            if(crisp.variable.name.equals(targetedVariableName)){
                return crisp.getMembershipDegree(targetedSetName);
            }
        }
        return  -1.0; //not found
    }

    public String Fuzzification(){
        String returnString="Fuzzification";
        System.out.print("Fuzzification ");
        //Loop on each Crisp Value
        for (FuzzyLogicCrispValues crisp : crispValues) {
            FuzzyLogicVariable crispVariable = crisp.variable;
            //System.out.println(crispVariable.name);
            //Loop on sets for the crisp values and see which sets it breaks
            for (int i = 0; i < crispVariable.sets.size(); i++) {
                //System.out.println("\tSet: "+crispVariable.sets.get(i).name);
                //check if it is in range (>=first and <=last)
                if(crisp.value >= crispVariable.sets.get(i).values.get(0)   //first element
                    && crisp.value <= crispVariable.sets.get(i).values.get(crispVariable.sets.get(i).values.size()-1)){ //last element
                    //System.out.println("\tInside range");
                    //Calculate membership degree
                    ArrayList<Integer> x_coordinates=crispVariable.sets.get(i).values;
                    ArrayList<Integer> y_coordinates=crispVariable.sets.get(i).y_coordinates;

                    double x1,x2,y1,y2;
                    double m,c;
                    for (int j = 0; j < x_coordinates.size(); j++) {
                        //get 2 points that the crisp value is within range
                        if (crisp.value>=x_coordinates.get(j) && crisp.value<=x_coordinates.get(j+1)){
                            //between point [j,j+1]
                            //System.out.println("Between points: "+(j+1)+","+(j+2));
                            x1=Double.valueOf(x_coordinates.get(j));
                            x2=Double.valueOf(x_coordinates.get(j+1));
                            y1=Double.valueOf(y_coordinates.get(j));
                            y2=Double.valueOf(y_coordinates.get(j+1));
                            m=(y2-y1)/(x2-x1);
                            //System.out.println("Slope: "+m);
                            //c=y-mx
                            c=y1-(m*x1);
                            //System.out.println("C: "+c);
                            //get membership degree (y=mx+c)
                            double membershipDegree = (crisp.value*m)+c;
                            crisp.membershipDegree.add(membershipDegree);
                            crisp.sets_name.add(crispVariable.sets.get(i).name);
                            break;
                        }
                    }

                }
                //not in range => membership degree=0
                else{
                    //System.out.println("\tOutside range");
                    crisp.membershipDegree.add(0.0);
                    crisp.sets_name.add(crispVariable.sets.get(i).name);
                }
                //System.out.println("\tmembership degree: "+crisp.membershipDegree.get(i));
            }
        }
        System.out.println("=> done ");
        returnString+="=> done ";
        return returnString;
    }
    public String Inference(){
        String returnString="Inference ";
        //System.out.print("Inference ");
        //Loop over each rule
        for (FuzzyLogicRule rule : rules) {
            //loop over operators
            int i=0;
            for (String operator : rule.operators) {        //AND/OR
                //get 2 terms (of this operation) => 1 operator works on only 2 terms
                FuzzyLogicRule.InputRule term1 = rule.InputRules.get(i);
                FuzzyLogicRule.InputRule term2 = rule.InputRules.get(i+1);
                i+=2;
                System.out.println(term1.variable.name+"."+term1.targetedSet+" "+operator+" "+term2.variable.name+"."+term2.targetedSet);

                double term1MembershipDegree= getMembershipDegreeForCrisp(term1.variable.name,term1.targetedSet);
                double term2MembershipDegree=getMembershipDegreeForCrisp(term2.variable.name,term2.targetedSet);
                System.out.println("Term 1: "+term1MembershipDegree);
                System.out.println("Term 2: "+term2MembershipDegree);

                //if any of the terms is negated => (1-x)
                if(term1.notPresent){
                    System.out.println("Term 1 negated");
                    term1MembershipDegree=(1-term1MembershipDegree);
                }
                if(term2.notPresent){
                    System.out.println("Term 2 negated");
                    term2MembershipDegree=(1.0-term2MembershipDegree);
                }

                //perform operation
                if(operator.equals("OR")||operator.equals("or"))        //OR => get maximum
                {
                    rule.calculatedOutput=Math.max(term1MembershipDegree, term2MembershipDegree);
                }
                else{ //AND => get minimum
                    rule.calculatedOutput=Math.min(term1MembershipDegree, term2MembershipDegree);
                }
                System.out.println("Final Output: "+rule.calculatedOutput);
            }
        }

        returnString+="=> done";
        return returnString;
        //System.out.println("=> done ");
    }
    public String Defuzzification(){
        String returnString="Defuzzification ";
        //Calculate weighted average for "OUT" variables ; for EACH set
        for (FuzzyLogicVariable variable : variables) {
            if(variable.type.equals("OUT")){
                for (FuzzyLogicSets set: variable.sets) {
                    set.calculateWeightedAverage();
                }
            }
        }

        double sumOfOutput=0.0;
        double numerator=0.0;
        //Substituting in the weighted average equation
        for (FuzzyLogicRule rule:rules) {
            sumOfOutput+=rule.calculatedOutput;
            for (FuzzyLogicVariable variable : variables) {
                if(variable.type.equals("OUT")){
                    for (FuzzyLogicSets set: variable.sets) {
                        if(rule.outputValue.equals(set.name)){
                            numerator+=(rule.calculatedOutput*set.weightedAverage);
                            System.out.println(set.name+":"+rule.calculatedOutput+"*"+set.weightedAverage);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(numerator+"/"+sumOfOutput);
        this.calulatedOutputValue=numerator/sumOfOutput;
        System.out.println("Calculated output: "+this.calulatedOutputValue);

        returnString+="=> done";
        return returnString;
    }

    public void Run(){
        Fuzzification();
        Inference();
        Defuzzification();
    }

}
