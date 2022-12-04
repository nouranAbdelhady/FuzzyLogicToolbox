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
}
