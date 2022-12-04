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
                System.out.println("Set: "+crispVariable.sets.get(i).name);
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
                System.out.println("membershipDegree: "+crisp.membershipDegree.get(i));
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
