
public interface IndividualBackendInterface <T extends Comparable<T>>{
    /*
     * public class Car{
     *  String model;
     *  String brand;
     *  int year;
     *  int price;
     *  int mileage;
     *  
     *  public Car{
     *  this.model = "";
     *  this.brand = "";
     *  this.year = 0;
     *  this.price = 0;
     *  this.mileage = 0;
     *  }
     * }
     */
    String getModel();
    void setModel();
    
    String getBrand();
    void setBrand();
    
    int getYear();
    void setYear();
    
    int getPrice();
    void setPrice();
    
    int getMileage();
    void setMileage();
    
    T readDataFile();
    SortedCollection<T> getMinMileage;
    SortedCollection<T> getMileageThreshold;
    
}
