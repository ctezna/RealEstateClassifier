package aptclassifier;
import java.util.*;
import java.io.*;
/**
 * collection of properties
 * using arraylist
 * @Author Carlos F. Tezna
 * @Version Consumer Edition 1.0
 */
public class Apartments
{
    ArrayList<Property> apartmentList = new ArrayList<>();
    String e = " ";
    String s = " ";
    String a = " ";
    String b = " ";
    String ba = " ";
    String f = " ";
    String label = " ";
    double estrato = 0;
    double size = 0;
    double age = 0;
    double beds = 0;
    double baths = 0;
    double floor = 0;
    double [] aptData = new double [6];
    Conversions convert = new Conversions();
    public Apartments(){
    }
    /**@return ArrayList of apts
     *
     */
    public ArrayList<Property> convertFile () throws FileNotFoundException {
        Scanner input = new Scanner(new File("aptsList.txt"));//reads file
        while(input.hasNextLine()){
            String line = input.nextLine();
            for(int i = 0; i < line.length(); i++){
                char start = line.charAt(i);
                if(start == '{'){//only grabs info insde curly braket
                    Scanner lineScan = new Scanner(line.substring(i+1));
                    e = lineScan.next();
                    estrato = Double.parseDouble(e);
                    s = lineScan.next();
                    size = Double.parseDouble(s);
                    a = lineScan.next();
                    age = Double.parseDouble(a);
                    b = lineScan.next();
                    beds = Double.parseDouble(b);
                    ba = lineScan.next();
                    baths = Double.parseDouble(ba);
                    f = lineScan.next();
                    floor = Double.parseDouble(f);
                    label = lineScan.next();
                    break;
                }
            }
            //normalizes data prior to return
            estrato = Conversions.standardizeEstrato(estrato);
            size = Conversions.standardizeSize(size);
            age = Conversions.standardizeAge(age);
            beds = Conversions.standardizeBed(beds);
            baths = Conversions.standardizeBath(baths);
            floor = Conversions.standardizeFloor(floor);
            double [] aptData = {estrato, size, age, beds, baths, floor};
            apartmentList.add(new Property(aptData, label));
        }
        return apartmentList;
    }
}

