import java.util.ArrayList;
import java.util.Arrays;

public class FuzzyLogicSets {
    String name;
    String type;        //TRI-TRAP
    //x-coordinates
    ArrayList<Integer> values = new ArrayList<Integer>();
    ArrayList<Integer> y_coordinates;
    double weightedAverage=0;

    //For OUTPUT variables ONLY
    //Centroid*OutputOfRule
    Double numerator=0.0;

    FuzzyLogicSets(String name,String type){
        this.name=name;
        this.type=type;
        if(type.equals("TRAP")){
            y_coordinates= new ArrayList<Integer>(Arrays.asList(0,1,1,0));
        }
        else{
            //TRI
            y_coordinates= new ArrayList<Integer>(Arrays.asList(0,1,0));
        }
    }

    FuzzyLogicSets(String name,String type, ArrayList<Integer> values){
        this.name=name;
        this.type=type;
        if(type.equals("TRAP")){
            y_coordinates= new ArrayList<Integer>(Arrays.asList(0,1,1,0));
        }
        else{
            //TRI
            y_coordinates= new ArrayList<Integer>(Arrays.asList(0,1,0));
        }
        this.values=values;
    }

    public void calculateWeightedAverage(){
        //get sum of values
        weightedAverage=0;
        for (double value:values) {
            weightedAverage+=value;
        }
        //get weighted average (sum/count)
        weightedAverage=weightedAverage/ values.size();
    }

    public void addValue(Integer value){
        this.values.add(value);
    }

    @Override
    public String toString() {
        return "FuzzyLogicSets{" +
                "name='" + name + '\n' +
                ", type='" + type + '\n' +
                ", values=" + values + '\n' +
                ", y_coordinates=" + y_coordinates + '\n' +
                '}';
    }
}