import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Backend implements BackendInterface{
    IterableMultiKeyRBT<Car> rbt;
    @Override
    public void readData(String fileName) throws FileNotFoundException {
        rbt = new IterableMultiKeyRBT();
        File f = new File(fileName);
        if(!f.exists()) {
            throw new FileNotFoundException("File not found");
        }
        Scanner scnr = new Scanner(f);
        scnr.nextLine();
        
        while(scnr.hasNextLine()) {
            String data[] = scnr.nextLine().split(",");
            
            if(data.length == 11) {
              int price = Integer.parseInt(data[0].trim());
              String brand = data[1].trim();
              String model = data[2].trim();
              int year = Integer.parseInt(data[3].trim());
              String titleStatus = data[4].trim();
              double mileage = Double.parseDouble(data[5].trim());
              String color = data[6].trim();
              String vin = data[7].trim();
              int lot = Integer.parseInt(data[8].trim());
              String state = data[9].trim();
              String country = data[10].trim();
              
              Car car = new Car(price, brand, model, year, titleStatus, mileage, color, vin, lot, state, country);
              rbt.insertSingleKey(car);
          }
        }
        scnr.close();
        
    }

    @Override
    public ArrayList<Car> getLowestMileage(int numCars) {
//        rbt.setIterationStartPoint(null);
        ArrayList<Car> lowestMileage = new ArrayList<Car>();
        for(int i = 0; i < numCars; i++) {
            if(rbt.iterator().hasNext()) {
                lowestMileage.add(rbt.iterator().next());
            }
        }
//        System.out.println("*********" + rbt.toInOrderString());
            return lowestMileage;
    }
    @Override
    public ArrayList<Car> getMileageAbove(int minimumMileage) {
//        IterableMultiKeyRBT<Car> mileageAbove = new IterableMultiKeyRBT<Car>();
        ArrayList<Car> mileageAbove = new ArrayList<>();
        rbt.setIterationStartPoint(minimumMileage);
        
        while(rbt.iterator().hasNext()) {
            mileageAbove.add(rbt.iterator().next());
        }
        return mileageAbove;
    }

    public static void main(String[] args){
        Backend be = new Backend();
        Scanner scnr = new Scanner(System.in);
        FrontendPlaceholder fe  = new FrontendPlaceholder(be, scnr);
        fe.startApp();
    }
}