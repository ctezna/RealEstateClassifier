package aptclassifier;
import java.util.*;
import java.io.*;
/**
 * implementation of KNN algorithm
 * using Euclidean Distance
 *
 * @Author Carlos F. Tezna
 * @Version Consumer Edition 1.0
 */
public class KNN
{
    private int k; //number of neighbors
    private ArrayList<Object> labels; //set of classifications for apts
    private ArrayList<Property> apartments; //collection of apts
    static Apartments apts = new Apartments(); //instance of Apartments class
    Interface getQuery = new Interface();//instance of Interface class

    /**
     *
     * @param list of apartments
     * @param k The number of neighbors to use
     */
    public KNN(ArrayList<Property> apartments, int k){
        this.labels = new ArrayList<Object>(); //labels will be read as Objects
        this.k = k;//k will be defined automatically **see Interface.runQuery(...)
        this.apartments = apartments; //list of apartments each time 'this.' is called

        //cycle through apartments
        for(Property query : apartments){
            if(!labels.contains(query.getY())){ //if our labels ArrayList still hasnt saved our label
                labels.add(query.getY());//then add it to the list
            }
        }
    }

    /**
     * @param User input after normalization   **Conversions class
     * @return Array of nearest properties
     */
    private Property[] getNearestNeighborType(Property query){
        Property[] result = new Property[this.k];//creates new array of Property's - size k of instance
        double knearest = Double.MIN_VALUE;//very low number
        int index = 0;
        for(Property x : this.apartments){
            double distance = distance(query,x);//stores distance between query and each element in apartments
            if(result[result.length-1] == null){ //if not filled
                int j = 0;//counts to k
                while(j < result.length){//while j is less than k
                    if(result[j] == null){//if not filled
                        result[j] = x;//set current property in position j
                        break;
                    }
                    j++;
                }
                if(distance > knearest){//if distance larger then the last known smallest
                    index = j;//sets index to value of j
                    knearest = distance;//set new smallest distance
                }
            }
            else{//second if used for visual organization
                if(distance < knearest){//if distance smaller then the last known smallest
                    result[index] = x;//set current property in what ever index loop is on
                    double temp = 0.0;//acts as temp variable to hold distance
                    int pos = 0;//acts as position in result array
                    for(int j = 0; j < result.length; j++){
                        double dist = distance(result[j],query);
                        if(dist > temp){
                            temp = dist;
                            pos = j;
                        }
                    }
                    knearest = temp;
                    index = pos;
                }
            }
        }
        //prints closet neighbors
        //for (Property a : result){
        //System.out.println(a.getY());
        // }
        return result;
    }

    /**
     *
     * @param e Entry to be classifies
     * @return The class of the most probable class
     */
    public Object classify(Property query){
        HashMap<Object,Double> neighbors = new HashMap<Object,Double>();
        Property[] result = this.getNearestNeighborType(query);//property array from NearestNeighborType method
        for(int i = 0; i < result.length; i++){
            double distance = (KNN.distance(result[i], query));
            if(!neighbors.containsKey(result[i].getY())){//if map doesnt already have an element with result[i]'s label
                neighbors.put(result[i].getY(), distance);//set "label" as key and distance as value
            }
            else{//or if result[i]'s label is already in the map then place the new element with all the others
                neighbors.put(result[i].getY(),neighbors.get(result[i].getY())+distance);
            }//creates map with all the distinct "labels" found in "result" with all the distance's added up
        }
        //Find right choice
        Object label = null;
        double max = 0;
        for(Object o : neighbors.keySet()){
            if(neighbors.get(o) > max){//if sum of all distances (neighbors) for one object is more than anothers
                max = neighbors.get(o);//set max to current element's value
                label = o;//set label to current element's key
            }
        }

        return label;
    }

    /**
     * Calculates Euclidean Distance
     * @param query From
     * @param neighbor To
     * @return distance
     */
    public static double distance(Property query, Property neighbor){
        double dist = 0.0;//temp distance variable
        int length = query.getX().length;//dimensions in the vector "point"
        for(int i = 0; i < length; i++){
            double sums = query.getX()[i]-neighbor.getX()[i];
            dist = dist+(sums*sums);//summation of the difference of all dimensions squared
        }
        double distance = Math.sqrt(dist);//final distance
        return distance;
    }
}

