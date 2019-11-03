package aptclassifier;
/**
 * takes input data for apartments and houses
 * and standardizes it or reverses it
 *
 * @Author Carlos F. Tezna
 * @Version Consumer Edition 1.0
 */




public class Conversions
{
    public Conversions(){
    }
    /**standardize & reverse for estrato
     *
     */
    public static double standardizeEstrato (double estrato){
        double standardEstrato = 0.0;
        double range = 6.0 - 1.0;
        standardEstrato = (estrato - 1.0)/ range;
        //estratoList.add(standardEstrato);
        return standardEstrato;
    }
    public static int reverseEstrato (double standardEstrato){
        int estrato = 0;
        estrato = (int)(standardEstrato * 5) + 1;
        return estrato;
    }
    /**standardize & reverse for ages
     *
     */
    public static double standardizeAge (double age){
        double standardAge = 0.0;
        double range = 4.0-1.0;
        standardAge = (age - 1.0)/ range;
        //ageList.add(standardAge);
        return standardAge;
    }
    public static int reverseAge (double standardAge){
        int age = 0;
        age = (int)(standardAge * 3) + 1;
        return age;
    }
    /**standardize & reverse for bedrooms
     *
     */
    public static double standardizeBed (double bed){
        double standardBed = 0.0;
        double range = 7.0 - 1.0;
        standardBed = (bed - 1.0)/ range;
        //bedsList.add(standardBed);
        return standardBed;
    }
    public static int reverseBed (double standardBed){
        int bed = 0;
        bed = (int)(standardBed * 6) + 1;
        return bed;
    }
    /**standardize & reverse for bathrooms
     *
     */
    public static double standardizeBath (double bath){
        double standardBath = 0.0;
        double range = 9.0 - 1.0;
        standardBath = (bath - 1.0)/ range;
        //bathsList.add(standardBath);
        return standardBath;
    }
    public static int reverseBath (double standardBath){
        int bath = 0;
        bath = (int)(standardBath * 8) + 1;
        return bath;
    }
    /**standardize & reverse for floors
     *
     */
    public static double standardizeFloor (double floor){
        double standardFloor = 0.0;
        double range = 33.0 - 1.0;
        standardFloor = (floor - 1.0)/ range;
        //floorList.add(standardFloor);
        return standardFloor;
    }
    public static int reverseFloor (double standardFloor){
        int floor = 0;
        floor = (int)(standardFloor * 32) + 1;
        return floor;
    }
    /**standardize & reverse for size
     *
     */
    public static double standardizeSize (double size){
        double standardSize = 0.0;
        double range = 780.0 - 16.0;
        standardSize = (size - 16.0)/ range;
        //sizeList.add(standardSize);
        return standardSize;
    }
    public static int reverseSize (double standardSize){
        int size = 0;
        size = (int)(standardSize * 764) + 16;
        return size;
    }
    /**same functions as above just for houses
     *
     */
    public static double standardizeSizeHouse (double sizeHouse){
        double standardSizeHouse = 0.0;
        double range = 6450.0 - 45.0;
        standardSizeHouse = (sizeHouse - 45.0)/ range;
        return standardSizeHouse;
    }
    public static int reverseSizeHouse (double standardSize){
        int size = 0;
        size = (int)(standardSize * 6405) + 45;
        return size;
    }

    public static double standardizeBedsHouse (double bedsHouse){
        double standardBedsHouse = 0.0;
        double range = 11.0 - 2.0;
        standardBedsHouse = (bedsHouse - 2.0)/ range;
        return standardBedsHouse;
    }
    public static int reverseBedsHouse (double standardBed){
        int beds = 0;
        beds = (int)(standardBed * 9) + 2;
        return beds;
    }

    public static double standardizeBathsHouse (double bathsHouse){
        double standardBathsHouse = 0.0;
        double range = 10.0 - 1.0;
        standardBathsHouse = (bathsHouse - 1.0)/ range;
        return standardBathsHouse;
    }
    public static int reverseBathsHouse (double standardBath){
        int baths = 0;
        baths = (int)(standardBath * 9) + 1;
        return baths;
    }

    public static double standardizeFloorHouse (double floorHouse){
        double standardFloorsHouse = 0.0;
        double range = 4.0 - 1.0;
        standardFloorsHouse = (floorHouse - 1.0)/ range;
        return standardFloorsHouse;
    }
    public static int reverseFloorHouse (double standardFloor){
        int floor = 0;
        floor = (int)(standardFloor * 3) + 1;
        return floor;
    }
    /**@param user query to be normalized
       @return user query after normalization
       */
    public double[] convertApts (double[] aptQuery){
        double estrato = standardizeEstrato(aptQuery[0]);
        double size = standardizeSize(aptQuery[1]);
        double age = standardizeAge(aptQuery[2]);
        double bed = standardizeBed(aptQuery[3]);
        double bath = standardizeBath(aptQuery[4]);
        double floor = standardizeFloor(aptQuery[5]);
        double [] aptStandard = {estrato, size, age, bed, bath, floor};
        return aptStandard;

    }
    public double[] convertHouse (double[] houseQuery){
        double estrato = standardizeEstrato(houseQuery[0]);
        double size = standardizeSizeHouse(houseQuery[1]);
        double age = standardizeAge(houseQuery[2]);
        double bed = standardizeBedsHouse(houseQuery[3]);
        double bath = standardizeBathsHouse(houseQuery[4]);
        double floor = standardizeFloorHouse(houseQuery[5]);
        double [] houseStandard = {estrato, size, age, bed, bath, floor};
        return houseStandard;
    }
}
