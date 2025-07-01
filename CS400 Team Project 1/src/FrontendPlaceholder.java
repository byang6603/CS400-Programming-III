import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Implement the frontend functionality
public class FrontendPlaceholder implements FrontendInterface {
    //variable declaration for backend and scanner used for the class
    private BackendInterface backend;
    private Scanner scanner;

    public FrontendPlaceholder(BackendInterface backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }

    @Override
    /**
     * startApp method that takes user input and runs different methods based on
     * their input. If the user does not input a value that is listed an error message will
     * be printed and they will be asked to inpu another value.
     */
    public void startApp() {
        //while true loop just to make sure the options are looped through
        while (true) {
            displayMainMenu();
            //call method to get user input
            int choice = getUserChoice();
            //an input of 1 loads the car data
            if (choice == 1) {
                loadCarData();
            }
            //input of 2 helps users list a certain number of cars
            //with the lowest mileage
            else if (choice == 2) {
                listCarsWithLowestMileage();
            }
            //input of 3 helps user lists the cars that have a mileage
            //greater than a certain mileage
            else if (choice == 3) {
                listCarsWithMileageAboveThreshold();
            }
            //input of 4 closes the app for the user
            else if (choice == 4) {
                exitApp();
                break;
            }
            //any other input prints an error message and resets the loop
            else {
                System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    /**
     * displayMainMenu method prints out the different methods the user may
     * access, including the number they need to input to access it.
     */
    public void displayMainMenu() {
        System.out.println("Car Inventory App");
        System.out.println("1. Load Car Data");
        System.out.println("2. List Cars with Lowest Mileage");
        System.out.println("3. List Cars with Mileage Above Threshold");
        System.out.println("4. Exit");
    }

    /**
     * method that takes user input to see what method they would like
     * to access
     * @return the number of the method the user wants to use
     */
    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        //return the next input line as an int
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * method that takes the user input for a file name and loads the relevant
     * data from that file
     */
    public void loadCarData() {
        System.out.print("Enter the path to the data file: ");
        //get the file name from the user
        String filePath = scanner.nextLine();

        try {
            backend.readData(filePath);
            //if the file path is correct, a message will indicate that the
            //data has been loaded successfully
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            //if the file path is incorrect, a message will indicate that
            //the file has not been found
            System.out.println("Path file not found!");
        }
    }

    /**
     * method takes the user input for the number of entries the user would like
     * to be displayed, and prints them.
     */
    public void listCarsWithLowestMileage() {
        //ask the user for the number of inputs
        System.out.print("Enter the number of entries: ");
        int entries = Integer.parseInt(scanner.nextLine());
        //get the arraylist of the number of cars the user asked for
        System.out.println("Listing " + entries + " cars with the lowest mileage:");
        ArrayList<Car> listOfCars =  backend.getLowestMileage(entries);
        //for loop that prints each car that was grabbed from the arraylist
        for (Car car: listOfCars) {
            System.out.println(car.getBrand() + " " + car.getModel() + " " + car.getYear() + " " + car.getVin());
        }
        System.out.println("Cars listed successfully!");
    }

    /**
     * method takes the user input for the mileage threshold the user would like
     * to set as the minimum to see which cars have greater mileage.
     */
    public void listCarsWithMileageAboveThreshold() {
        //ask the user for mileage threshold they want
        System.out.print("Enter the mileage threshold: ");
        int threshold = Integer.parseInt(scanner.nextLine());
        //get the arraylist of the cars above the mileage threshold
        System.out.println("Listing cars with mileage above " + threshold + " miles...");
        ArrayList<Car> listOfCars = backend.getMileageAbove(threshold);
        ////for loop that prints each car that was grabbed from the arraylist
        for (Car car: listOfCars) {
            System.out.println(car.getBrand() + " " + car.getModel() + " " + car.getYear() + " " + car.getVin());
        }
        System.out.println("Cars listed successfully!");
    }

    /**
     * method that exits the app and closes the scanner.
     */
    public void exitApp() {
        System.out.println("Exiting the app.");
        scanner.close();
    }

    /**
     * main method to run the command loop
     * @param args
     */
    public static void main(String[] args) {
        Backend backend = new Backend(); // Implement the backend class
        Scanner scanner = new Scanner(System.in);
        FrontendPlaceholder frontend = new FrontendPlaceholder(backend, scanner);

        frontend.startApp();
    }
}
