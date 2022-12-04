import java.util.ArrayList;

public class FuzzyLogicCrispValues {
    FuzzyLogicVariable variable;
    Integer value;
    ArrayList<Double> membershipDegree = new ArrayList<Double>();
    ArrayList<String> sets_name = new ArrayList<String>();

    public FuzzyLogicCrispValues(FuzzyLogicVariable variable, Integer value) {
        this.variable=variable;
        this.value=value;
    }

    public Double getMembershipDegree(String targetedSet){
        for (int i = 0; i < sets_name.size(); i++) {
            if(targetedSet.equals(sets_name.get(i))){
                return membershipDegree.get(i);
            }
        }
        return -1.0;  //not found
    }
}
