import java.util.ArrayList;
import java.util.Arrays;

public class FuzzyLogicSets {
    String name;
    String type;
    //x-coordinates
    ArrayList<Integer> values = new ArrayList<Integer>();
    ArrayList<Integer> y_coordinates;

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
