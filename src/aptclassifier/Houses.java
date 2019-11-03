package aptclassifier;
/**
 * Collection of houses (Property)
 * @Author Carlos F. Tezna
 * @Version Consumer Edition 1.0
 */
import java.util.*;
import java.io.*;
public class Houses
{
    ArrayList<Property> houseList = new ArrayList<>();
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
    double [] houseData = new double [6];
    Conversions convert = new Conversions();
    public Houses(){
    }
    /**@returns ArrayList of houses from .txtfile
     *
     */
    public ArrayList<Property> convertFile () throws FileNotFoundException {
        Scanner input = new Scanner(new File("houseList.txt"));//reads file
        while(input.hasNextLine()){
            String line = input.nextLine();
            for(int i = 0; i < line.length(); i++){
                char start = line.charAt(i);
                if(start == '{'){//only grabs info inside curly braket
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
            size = Conversions.standardizeSizeHouse(size);
            age = Conversions.standardizeAge(age);
            beds = Conversions.standardizeBedsHouse(beds);
            baths = Conversions.standardizeBathsHouse(baths);
            floor = Conversions.standardizeFloorHouse(floor);
            double [] houseData = {estrato, size, age, beds, baths, floor};
            houseList.add(new Property(houseData, label));


        }
        return houseList;
    }
}
