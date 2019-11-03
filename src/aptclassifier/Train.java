package aptclassifier;
/**
 * Will train my KNN model
 *
 * @author Carlos F. Tezna
 * @version Pre-release Consumer Edition 1.0
 */
import java.util.*;
import java.io.*;
import java.awt.*;
public class Train
{
    Conversions convert = new Conversions();
    private static Scanner in = new Scanner(System.in);
    static ArrayList<Property> apartmentList = new ArrayList<>();
    static Apartments apts = new Apartments();
    static Interface getQuery = new Interface();
    static ArrayList<Property> train = new ArrayList<>();
    static ArrayList<Property> test = new ArrayList<>();
    static ArrayList<Property> accuracyTest = new ArrayList<>();
    static ArrayList<Property> prediction = new ArrayList<>();
    public Train(){
    }
    /**@return Shuffled list of apartments
     *
     */
    public ArrayList<Property> shuffle() throws Exception{
        apartmentList = apts.convertFile();
        Collections.shuffle(apartmentList);
        return apartmentList;
    }
    /**@return list of 33 test properties to be used for testing
     *
     */
    public ArrayList<Property> sortTest() throws Exception{
        shuffle();
        String ignore = "Ignore";
        for(int i = 0; i < 33; i++){
            accuracyTest.add(apartmentList.get(i));
        }
        for(Property a: accuracyTest){
            Property blank = new Property(a.getX(), ignore);
            test.add(blank);
        }
        return test;
    }
    /**@return list of train apts for training
     *
     */
    public ArrayList<Property> sortTrain() throws Exception{
        shuffle();
        for(int i = 32; i < 204; i++){
            train.add(apartmentList.get(i));
        }
        return train;
    }
    /**executes testing of the model
     *
     */
    public void test() throws Exception{
        ArrayList<Property> test = sortTest();
        double count = 0.0;
        double accuracy = 0.0;
        double temp1 = 33.0;
        for(Property t: test){
            KNN run = new KNN(sortTrain(), 30);
            Property labeled = new Property(t.getX(), run.classify(t));
            if(labeled.getY() != null){
                System.out.println("Apartment is considered: "+ labeled.getY());
                count++;
            }else{
                run.classify(labeled);
            }
        }
        double temp = count/temp1;
        accuracy = temp *100;
        System.out.println("Train accuracy: " + accuracy + "%");
    }

    public static void main(String [] args)throws Exception{
        Train train = new Train();
        train.test();
    }
}

