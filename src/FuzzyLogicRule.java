import java.util.ArrayList;

public class FuzzyLogicRule {

    static class InputRule {
        FuzzyLogicVariable variable;

        String targetedSet;
        Boolean notPresent;
        public InputRule(FuzzyLogicVariable variable, String targetedSet, Boolean notPresent) {
            this.variable = variable;
            this.targetedSet = targetedSet;
            this.notPresent = notPresent;
        }
        @Override
        public String toString() {
            return "InputRule{" +
                    "variable=" + variable +'\n'+
                    ", targetedSet='" + targetedSet +'\n'+
                    ", notPresent=" + notPresent +'\n'+
                    '}';
        }
    }
    ArrayList<InputRule> InputRules = new ArrayList<InputRule>();
    ArrayList<String> operators = new ArrayList<String>();
    FuzzyLogicVariable output;
    String outputValue;  //one of the output sets

    double calculatedOutput;
    public FuzzyLogicRule(ArrayList<InputRule> inputRules, ArrayList<String> operators, FuzzyLogicVariable output, String outputValue) {
        InputRules = inputRules;
        this.operators = operators;
        this.output = output;
        this.outputValue = outputValue;
    }
    @Override
    public String toString() {
        return "FuzzyLogicRule{" +
                "InputRules=" + InputRules +'\n'+
                ", operators=" + operators +'\n'+
                ", output=" + output +'\n'+
                ", outputValue='" + outputValue +'\n'+
                ", calculatedOutput=" + calculatedOutput +'\n'+
                '}';
    }
}
