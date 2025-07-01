public class Car implements CarInterface, Comparable<Car> {
    private int price;
    private String brand;
    private String model;
    private int year;
    private double mileage;
    private String color;
    private String vin;
    private int lot;
    private String state;
    private String country;
    private String condition;

    public Car(int price, String brand, String model, int year, String condition, double mileage,
               String color, String vin, int lot, String state, String country) {
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.vin = vin;
        this.lot = lot;
        this.state = state;
        this.country = country;
        this.condition = condition;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getMileage() {
        return mileage;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getVin() {
        return vin;
    }

    @Override
    public int getLot() {
        return lot;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCondition() {
        return condition;
    }
    public int compareTo(Car otherCar) {
        return (int) (this.getMileage() - otherCar.getMileage());
    }
}