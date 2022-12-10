import java.util.ArrayList;

public class FuzzyLogicVariable {
    String name;
    String type;        //IN-OUT
    int lowerRange;
    int upperRange;
    ArrayList<FuzzyLogicSets> sets = new ArrayList<FuzzyLogicSets>();

    //For OUTPUT variables ONLY
    //denominator of the weighted average equation
    double sumOfOutput=0.0;
    String outputSet;
    double calculatedOutputValue;
    public FuzzyLogicVariable(String name, String type, int lowerRange, int upperRange) {
        this.name = name;
        this.type = type;
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "FuzzyLogicVariable{" +
                "name='" + name + '\n' +
                ", type='" + type + '\n' +
                ", lowerRange=" + lowerRange + '\n' +
                ", upperRange=" + upperRange + '\n' +
                ", sets=" + sets + '\n' +
                '}';
    }
}
