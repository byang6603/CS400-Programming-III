import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BackendPlaceHolder implements BackendInterface{

    public BackendPlaceHolder() {
        
        
    }
    @Override
    public void readData(String fileName) throws FileNotFoundException {
        try{
            File cars = new File(fileName);
            Scanner scnr = new Scanner(cars).useDelimiter(",");
            RedBlackTree rbt = new RedBlackTree();
            while(scnr.hasNext()) {
                Car car = new Car(scnr.nextInt(), scnr.next(), scnr.next(), scnr.nextInt(), scnr.next(), scnr.nextDouble(), scnr.next(), scnr.next(), scnr.nextInt(), scnr.next(), scnr.next(), scnr.next());
                rbt.insert(car.getMileage());
            }
        }catch(Exception e) {
            
        }
        
    }

    @Override
    public ArrayList<Car> getLowestMileage(int maxMileage) {
        ArrayList<Car> lowestMileage = new ArrayList<>();
        Car car1 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 100000, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car2 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 6, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car3 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 8532, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car4 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 139028, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car5 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 7839, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car6 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 15902, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");

        lowestMileage.add(car2);
        lowestMileage.add(car3);
        lowestMileage.add(car5);
        return lowestMileage;
    }

    @Override
    public ArrayList<Car> getMileageAbove(int minimumMileage) {
        ArrayList<Car> mileageAbove = new ArrayList<>();
        Car car1 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 100000, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car2 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 6, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car3 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 8532, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car4 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 139028, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car5 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 7839, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");
        Car car6 = new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 15902, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa", "new");

        mileageAbove.add(car1);
        mileageAbove.add(car4);
        mileageAbove.add(car6);
        return mileageAbove;
    }
    
}
