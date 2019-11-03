package aptclassifier;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 * User interface class
 * @Author Carlos F.Tezna
 * @Version Consumer Edition 1.0
 *
 * Interface interacts with the user recieving input and passing it along
 */
public class Interface
{
    Conversions convert = new Conversions();//instance used for all conversions
    private static Scanner in = new Scanner(System.in);//allows user input
    ArrayList<Property> apartmentList = new ArrayList<>();//database of apartments, used for writing .txt file
    Apartments apts = new Apartments();//instance of Apartments, reads .txt file
    Houses house = new Houses();//instance of Houses, reads .txt file
    ArrayList<Property> houseList = new ArrayList<>();//database of houses, used for writing .txt file
    static Interface getQuery = new Interface();//instace of interface-- see main
    public Interface(){
        //constructor
    }

    /**
     * main class starts the sequence of calls in execution process
     */
    public static void main(String [] args){
        getQuery.languageSelection();
    }
    /**
     * prompts user to pick language or close program
     */
    public void languageSelection(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPlease Select Language/Idioma");
        System.out.println("1- English");
        System.out.println("2- Español");
        System.out.println("3- Close");
        double enter = in.nextDouble();
        do{
            if (enter == 1){//if user picks english
                propertyTypeEnglish();
                break;
            }else if (enter == 2){//if user picks spanish
                propertyTypeSpanish();
                break;
            }else if (enter == 3){//if user picks close
                System.exit(0);
            }
        } while(enter > 0 && enter < 4);
    }

    /**
     * prompts user to choose type of property or close program
     */
    public void propertyTypeEnglish(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPlease Select Type of Property");
        System.out.println("1- Apartment");
        System.out.println("2- House");
        System.out.println("3- Public Space");
        System.out.println("4 - Close");
        double enter = in.nextDouble();
        do{
            if (enter == 1){//if user picks apartment
                aptForm();
                break;
            }else if (enter == 2){//if user picks house
                houseForm();
                break;
            }else if (enter == 3){//if user picks public space
                pubSpaceForm();
                break;
            }else if (enter == 4){//if user closes
                System.exit(0);
            }
        }while(enter > 0 && enter < 5);
    }

    /**
     * same as above method except only when user picks spanish
     */
    public void propertyTypeSpanish(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPor Favor Escoje Tipo de Propiedad");
        System.out.println("1- Apartamento");
        System.out.println("2- Casa");
        System.out.println("3- Espacio Público");
        System.out.println("4- Cerrar");
        double enter = in.nextDouble();
        do{
            if (enter == 1){
                aptFormSpanish();
                break;
            }else if (enter == 2){
                houseFormSpanish();
                break;
            }else if (enter == 3){
                pubSpaceForm();
                break;
            }else if (enter == 4){
                System.exit(0);
            }
        }while(enter > 0 && enter < 5);
    }

    /**@return Property query after user enters info
     *  has user enter all required information to run program
     */
    public Property aptForm(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPlease Enter the Following Information");
        System.out.println("Estrato 1-6: ");
        double estrato = in.nextDouble();
        System.out.println("Size in m2: ");
        double size = in.nextDouble();
        System.out.println("Select Age in years: ");
        System.out.println("               1- 0-5 yrs");
        System.out.println("               2- 5-10 yrs");
        System.out.println("               3- 10-20 yrs");
        System.out.println("               4- 20+ yrs");
        double age = in.nextDouble();
        System.out.println("Bedrooms: ");
        double beds = in.nextDouble();
        System.out.println("Bathrooms: ");
        double baths = in.nextDouble();
        System.out.println("Floor: ");
        double floor = in.nextDouble();
        //normalizes user input
        double [] stats = {Conversions.standardizeEstrato(estrato), Conversions.standardizeSize(size), Conversions.standardizeAge(age),Conversions.standardizeBed(beds),Conversions.standardizeBath(baths),Conversions.standardizeFloor(floor)};
        Property query = new Property(stats, "Ignore");//creates Property to be returned
        String ageNormal = new String();//to change digit entered to text
        if (age == 1){//query is 0-5 yrs old
            ageNormal = "0-5 years";
        }
        if (age == 2){//query is 5-10 yrs old
            ageNormal = "5-10 years";
        }
        if (age == 3){//query is 10-20 yrs old
            ageNormal = "10-20 years";
        }
        if (age == 4){//query is 20 or more yrs old
            ageNormal = "20+ years";
        }
        System.out.println("                Mainline || Studios");
        System.out.println("\nDo you wish to continue with the following data?");
        System.out.println("    Estrato: " + (int)estrato + "\n    Size: " + (int)size + "m2" +
            "\n    Age: " + ageNormal + "\n    Bedrooms: " + (int)beds + "\n    Bathrooms: " + (int)baths +
            "\n    Floor: " + (int) floor);
        System.out.println("1- Continue");
        System.out.println("2- Back");
        double enter = in.nextDouble();
        do{
            if (enter == 1){//if user wants to continue with entered data
                runQuery(query);//KNN
                break;
            }else if (enter == 2){//if user wants to go back
                propertyTypeEnglish();
            }
        }while(enter > 0 && enter < 3);
        return query;
    }
    /**persistence achieved by saving .txt file with new data
     *
     */
    public void addQuery(Property labeled, ArrayList<Property> apartmentList) throws FileNotFoundException{
        PrintStream out = new PrintStream(new File("aptsList.txt"));//rewrites same file
        apartmentList.add(labeled);
        int i = 1;
        for(Property a: apartmentList){
            int estrato = Conversions.reverseEstrato(a.getX()[0]);
            int size = Conversions.reverseSize(a.getX()[1]);
            int age = Conversions.reverseAge(a.getX()[2]);
            int beds = Conversions.reverseBed(a.getX()[3]);
            int baths = Conversions.reverseBath(a.getX()[4]);
            int floor = Conversions.reverseFloor(a.getX()[5]);
            String label = a.getY().toString();
            out.println("Apartment #"+ i+ "- {" + estrato + "\t" + size + "\t" + age + "\t" + beds + "\t" + baths + "\t" + floor + "\t" + label + " },");
            i++;
        }
    }

    public void addQueryHouse(Property labeled, ArrayList<Property> houseList) throws FileNotFoundException{
        PrintStream out = new PrintStream(new File("houseList.txt"));//rewrties same file
        houseList.add(labeled);
        int i = 1;
        for(Property a: houseList){
            int estrato = Conversions.reverseEstrato(a.getX()[0]);
            int size = Conversions.reverseSizeHouse(a.getX()[1]);
            int age = Conversions.reverseAge(a.getX()[2]);
            int beds = Conversions.reverseBedsHouse(a.getX()[3]);
            int baths = Conversions.reverseBathsHouse(a.getX()[4]);
            int floor = Conversions.reverseFloorHouse(a.getX()[5]);
            String label = a.getY().toString();
            out.println("House #"+ i+ "- {" + estrato + "\t" + size + "\t" + age + "\t" + beds + "\t" + baths + "\t" + floor + "\t" + label + " },");
            i++;
        }
    }
    /**calls knn algorithm to label query
     *
     */
    public void runQuery(Property query) {
        try {
            apartmentList = apts.convertFile();
            int k = (int)Math.sqrt(apartmentList.size()) + 1;//automatically selects k based on amount of data
            KNN run = new KNN(apartmentList, k);//new instance of KNN with apts
            Property labeled = new Property(query.getX(),run.classify(query));//run.classify(...) produces a Obj
            System.out.println("                Mainline || Studios");
            System.out.println("\n      Apartment is considered: "+ labeled.getY() + " class");
            String label = labeled.getY().toString();
            if(label == "Lower"){
                System.out.println("\n      Apartment is worth: $0-100,000,000");
            }
            addQuery(labeled, apartmentList);//see above method
            System.out.println("\n       Apartment saved to our collection!");
            System.out.println("              1- Info    ||   2- Close");
            double enter = in.nextDouble();
            do{
                if (enter == 1){
                    info();
                    break;
                }else if (enter == 2){
                    System.exit(0);
                }
            }while(enter > 0 && enter < 3);
        }

        catch(Exception e){
            System.out.println("File not found");
        }
    }
    /**Software info
     *
     */
    public void info(){
        System.out.println("                Mainline || Studios");
        System.out.println("             Consumer Edition 1.0  2016");
        System.out.println("         All Rights Reserved Carlos F. Tezna");
        System.out.println("            Contact: cteznas@eafit.edu.co");
        System.out.println("                      1 - Close");
        if (in.nextDouble() == 1){
            System.exit(0);
        }
    }
    /**same as runQuery
     *
     */
    public void runQuerySpanish(Property query) {
        try {
            apartmentList = apts.convertFile();
            int k = (int)Math.sqrt(apartmentList.size()) + 1;
            KNN run = new KNN(apartmentList, k);
            Property labeled = new Property(query.getX(), run.classify(query));
            System.out.println("                Mainline || Studios");
            System.out.println("\n        Apartamento es considerado:"+ labeled.getY() + " class");
            addQuery(labeled, apartmentList);
            System.out.println("\n      ¡Apartamento guardado en nuestra colleción!");
            System.out.println("       1- Información    ||   2- Cerrar");
            double enter = in.nextDouble();
            do{
                if (enter == 1){
                    info();
                    break;
                }else if (enter == 2){
                    System.exit(0);
                }
            }while(enter > 0 && enter < 3);
        }

        catch(Exception e){
            System.out.println("File not found");
        }
    }
    /**same as aptForm but for house
     *
     */
    public Property houseForm(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPlease Enter the Following Information");
        System.out.println("Estrato 1-6: ");
        double estrato = in.nextDouble();
        System.out.println("Size in m2: ");
        double size = in.nextDouble();
        System.out.println("Select Age in years: ");
        System.out.println("               1- 0-5 yrs");
        System.out.println("               2- 5-10 yrs");
        System.out.println("               3- 10-20 yrs");
        System.out.println("               4- 20+ yrs");
        double age = in.nextDouble();
        System.out.println("Bedrooms: ");
        double beds = in.nextDouble();
        System.out.println("Bathrooms: ");
        double baths = in.nextDouble();
        System.out.println("Floors: ");
        double floor = in.nextDouble();
        double [] stats = {Conversions.standardizeEstrato(estrato),Conversions.standardizeSizeHouse(size),Conversions.standardizeAge(age),Conversions.standardizeBedsHouse(beds),Conversions.standardizeBathsHouse(baths),Conversions.standardizeFloorHouse(floor)};
        Property houseQuery = new Property(stats, "Ignore");
        String ageNormal = new String();
        if (age == 1){
            ageNormal = "0-5 years";
        }
        if (age == 2){
            ageNormal = "5-10 years";
        }
        if (age == 3){
            ageNormal = "10-20 years";
        }
        if (age == 4){
            ageNormal = "20+ years";
        }
        System.out.println("                Mainline || Studios");
        System.out.println("\nDo you wish to continue with the following data?");
        System.out.println("    Estrato: " + (int)estrato + "\n Size: " + (int)size + "m2" +
            "\n    Age: " + ageNormal + "\n    Bedrooms: " + (int)beds + "\n    Bathrooms: " + (int)baths +
            "\n    Floors: " + (int) floor);
        System.out.println("1- Continue");
        System.out.println("2- Back");
        double enter = in.nextDouble();
        do{
            if (enter == 1){
                runQueryHouse(houseQuery);
                break;
            }else if (enter == 2){
                propertyTypeEnglish();
            }
        }while(enter > 0 && enter < 3);
        return houseQuery;
    }
    /**same as runQuery except for house
     *
     */
    public void runQueryHouse(Property houseQuery) {
        try {
            houseList = house.convertFile();
            int k = (int)Math.sqrt(houseList.size()) + 1;//automatically updates
            KNNHouse run = new KNNHouse(houseList, k);
            Property labeled = new Property(houseQuery.getX(),run.classify(houseQuery));
            System.out.println("                Mainline || Studios");
            System.out.println("\n     House is considered: "+ labeled.getY() + " class");
            addQueryHouse(labeled, houseList);
            System.out.println("\n           House saved to our collection!");
            System.out.println("              1- Info    ||   2- Close");
            double enter = in.nextDouble();
            do{
                if (enter == 1){
                    info();
                    break;
                }else if (enter == 2){
                    System.exit(0);
                }
            }while(enter > 0 && enter < 3);
        }

        catch(Exception e){
            System.out.println("File not found");
        }
    }

    public void runQueryHouseSpanish(Property houseQuery) {
        try {
            houseList = house.convertFile();
            int k = (int)Math.sqrt(houseList.size()) + 1;//automatically updates
            KNNHouse run = new KNNHouse(houseList, k);
            Property labeled = new Property(houseQuery.getX(),run.classify(houseQuery));
            System.out.println("                Mainline || Studios");
            System.out.println("\n      Casa es considerado: "+ labeled.getY() + " class");
            addQueryHouse(labeled, houseList);
            System.out.println("\n     ¡Casa guardada en nuestra collecón!");
            System.out.println("       1- Información    ||   2- Cerrar");
            double enter = in.nextDouble();
            do{
                if (enter == 1){
                    info();
                    break;
                }else if (enter == 2){
                    System.exit(0);
                }
            }while(enter > 0 && enter < 3);
        }

        catch(Exception e){
            System.out.println("File not found");
        }
    }
    /**to be developed in Enterprise Edition 2.0
     *
     */
    public void pubSpaceForm(){
        System.out.println("Only available on Enterprise Edition");
        System.out.println("       Mainline || Studios            ");
        System.out.println("1- Back");
        System.out.println("2- Close");
        double enter = in.nextDouble();
        do{
            if (enter == 1){
                languageSelection();
                break;
            }else if (enter == 2){
                System.exit(0);
            }
        }while(enter > 0 && enter < 3);
    }
    /**same as aptForm
     *
     */
    public Property aptFormSpanish(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPor favor llena toda la información");
        System.out.println("Estrato 1-6: ");
        double estrato = in.nextDouble();
        System.out.println("Tamaño en m2: ");
        double size = in.nextDouble();
        System.out.println("Escoje edad en años: ");
        System.out.println("               1- 0-5 años");
        System.out.println("               2- 5-10 años");
        System.out.println("               3- 10-20 años");
        System.out.println("               4- 20+ años");
        double age = in.nextDouble();
        System.out.println("Habitaciones: ");
        double beds = in.nextDouble();
        System.out.println("Baños: ");
        double baths = in.nextDouble();
        System.out.println("Piso: ");
        double floor = in.nextDouble();
        double [] stats = {Conversions.standardizeEstrato(estrato),Conversions.standardizeSize(size),Conversions.standardizeAge(age),Conversions.standardizeBed(beds),Conversions.standardizeBath(baths),Conversions.standardizeFloor(floor)};
        Property query = new Property(stats, "Ignore");
        String ageNormal = new String();
        if (age == 1){
            ageNormal = "0-5 años";
        }
        if (age == 2){
            ageNormal = "5-10 años";
        }
        if (age == 3){
            ageNormal = "10-20 años";
        }
        if (age == 4){
            ageNormal = "20+ años";
        }
        System.out.println("                Mainline || Studios");
        System.out.println("\n¿Deseas continuar con la siguiente información?");
        System.out.println("    Estrato: " + (int)estrato + "\n     Tamaño: " + (int)size + "m2" +
            "\n    Edad: " + ageNormal + "\n    Habitaciones: " + (int)beds + "\n    Baños: " + (int)baths +
            "\n    Piso: " + (int) floor);
        System.out.println("1- Continuar");
        System.out.println("2- Atras");
        double enter = in.nextDouble();
        do{
            if (enter == 1){
                runQuerySpanish(query);
                break;
            }else if (enter == 2){
                propertyTypeSpanish();
            }
        }while(enter > 0 && enter < 3);
        return query;
    }
    /**same as houseForm
     *
     */
    public Property houseFormSpanish(){
        System.out.println("                Mainline || Studios");
        System.out.println("\nPor favor llena toda la información");
        System.out.println("Estrato 1-6: ");
        double estrato = in.nextDouble();
        System.out.println("Tamaño en m2: ");
        double size = in.nextDouble();
        System.out.println("Escoje edad en años: ");
        System.out.println("               1- 0-5 años");
        System.out.println("               2- 5-10 años");
        System.out.println("               3- 10-20 años");
        System.out.println("               4- 20+ años");
        double age = in.nextDouble();
        System.out.println("Habitaciones: ");
        double beds = in.nextDouble();
        System.out.println("Baños: ");
        double baths = in.nextDouble();
        System.out.println("Pisos: ");
        double floor = in.nextDouble();
        double [] stats = {Conversions.standardizeEstrato(estrato),Conversions.standardizeSizeHouse(size),Conversions.standardizeAge(age),Conversions.standardizeBedsHouse(beds),Conversions.standardizeBathsHouse(baths),Conversions.standardizeFloorHouse(floor)};
        Property houseQuery = new Property(stats, "Ignore");
        String ageNormal = new String();
        if (age == 1){
            ageNormal = "0-5 años";
        }
        if (age == 2){
            ageNormal = "5-10 años";
        }
        if (age == 3){
            ageNormal = "10-20 años";
        }
        if (age == 4){
            ageNormal = "20+ años";
        }
        System.out.println("                Mainline || Studios");
        System.out.println("\n¿Deseas continuar con la siguiente información?");
        System.out.println("    Estrato: " + (int)estrato + "\n    Tamaño: " + (int)size + "m2" +
            "\n    Edad: " + ageNormal + "\n    Habitaciones: " +(int)beds + "\n    Baños: " + (int)baths +
            "\n    Pisos: " + (int) floor);
        System.out.println("1- Continuar");
        System.out.println("2- Atras");
        double enter = in.nextDouble();
        do{
            if (enter == 1){
                runQueryHouseSpanish(houseQuery);
                break;
            }else if (enter == 2){
                propertyTypeSpanish();
            }
        }while(enter > 0 && enter < 3);
        return houseQuery;
    }
}

