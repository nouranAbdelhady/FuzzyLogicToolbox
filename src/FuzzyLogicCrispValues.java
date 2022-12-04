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
}
