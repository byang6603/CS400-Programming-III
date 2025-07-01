import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface BackendInterface{

public void readData(String fileName) throws FileNotFoundException;
public ArrayList<Car> getLowestMileage(int numberOfEntries);
public ArrayList<Car> getMileageAbove(int minimumMileage);

}