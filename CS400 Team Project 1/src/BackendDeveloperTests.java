import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackendDeveloperTests{
    
    @Test
    void testGetLowestMileage() {
        BackendPlaceHolder be = new BackendPlaceHolder();
        int edgeCaseInt = -1;
        ArrayList actual = be.getLowestMileage(edgeCaseInt);
        //-1 is not possible for mileage, return arraylist should be empty
        ArrayList<Car> expected = new ArrayList<>();
        //expecting an empty arraylist
        //for testing the arraylist will be filled
        expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 6, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
        expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 8532, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
        expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 7839, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
        
        Assertions.assertTrue(!expected.equals(actual));
        //arraylists contain same cars
        //cars are not comparable yet
    }
    
    @Test
    void testGetMileageAbove() {
        BackendPlaceHolder be = new BackendPlaceHolder();
        int edgeCaseInt = -1;
        //mileage cannot be negative
        ArrayList actual = be.getMileageAbove(edgeCaseInt);
        ArrayList<Car> expected = new ArrayList<>();
        //expected should be an empty arraylist, for testing it will be filled
        expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 100000, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
        expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 139028, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
        expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 15902, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
        Assertions.assertTrue(!expected.equals(actual));
        //arraylist contains same cars 
        //cars are not comparable yet
    }
    
    @Test
    void testContains() {
        BackendPlaceHolder be = new BackendPlaceHolder();
        int edgeCaseInt = -1;
        Assertions.assertTrue(!be.getLowestMileage(10000).contains(edgeCaseInt));
        //sees if back end contains a car with -1 miles on it
        //-1 is not a possible mileage
        //for testing this will also come out to false
    }
    
    @Test
    void testRBTSize() {
        BackendPlaceHolder be = new BackendPlaceHolder();
        //do stuff to add to the tree in backend
        Assertions.assertEquals(3, be.getLowestMileage(10000).size());
        //for testing this will be true
    }
    
    @Test
    void testIsEmpty() {
        BackendPlaceHolder be = new BackendPlaceHolder();
        //add cars to rbt
        int edgeCaseInt = -1;
        Assertions.assertTrue(!be.getLowestMileage(10000).isEmpty());
        //removes car with a mileage of -1
        //if cannot remove car with mileage of -1, then return true and pass test
        //for testing isEmpty will allow test to pass
    }
    @Test
        void testIntegrationListCarsWithMileageAboveThreshold(){
           Frontend fe = new FrontEnd();
           fe.loadCarData();
           ArrayList<Car> actual = fe.listCarsWithMileageAboveThreshold();
           ArrayList<Car> expected = new ArrayList<>();
           expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 100000, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
            expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 139028, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
            expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 15902, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
           Assertions.assertEquals(expected, actual);
           
     }
        @Test
        void testIntegrationListCarsWithLowestMileage(){
           Frontend fe = new Frontend();
           fe.loadCarData();
           ArrayList<Car> actual = fe.listCarsWithLowestMileage();
           ArrayList<Car> expected = new ArrayList<>();
           expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 6, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
           expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 8532, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
           expected.add(new Car(6300, "toyota", "cruiser", 2008, "clean vehicle", 7839, "black", "jtezu11f88k007763", 159348797, "new jersey", "usa"));
           Assertions.assertEquals(expected, actual);
}
