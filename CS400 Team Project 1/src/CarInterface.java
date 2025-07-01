public interface CarInterface <T extends Comparable<T>>{
    int getPrice();
    String getBrand();
    String getModel();
    int getYear();
    double getMileage();
    String getColor();
    String getVin();
    int getLot();
    String getState();
    String getCountry();
    String getCondition();
}