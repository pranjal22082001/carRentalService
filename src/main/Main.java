package main;

import models.Car;
import models.Customer;
import services.CarService;
import services.CustomerService;
import services.RentalService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService();
        CustomerService customerService = new CustomerService();
        RentalService rentalService = new RentalService(carService, customerService);

        // Add the cars available for rental along with the license plate
        carService.addCar(new Car("CYD123", "Toyota"));
        carService.addCar(new Car("DYC789", "Honda"));
        carService.addCar(new Car("ACD456", "Thar"));
        carService.addCar(new Car("YTF357", "Audi"));
        carService.addCar(new Car("OPT867", "Mercedes"));

        Scanner scanner = new Scanner(System.in);

        // Add the number of customers to be added 
        System.out.println("Enter the number of customers to add: ");
        int numberOfCustomers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Enter customer ID: ");
            String customerId = scanner.nextLine();
            System.out.println("Enter customer name: ");
            String customerName = scanner.nextLine();
            System.out.println("Enter customer phone number: ");
            String customerPhoneNumber = scanner.nextLine();

            customerService.addCustomer(new Customer(customerId, customerName, customerPhoneNumber));  
        }

        boolean running = true;
        while (running) {
            System.out.println("Choose an option: ");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. View all rentals");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter customer ID:");
                    String customerId = scanner.nextLine();
                    System.out.println("Enter car license plate:");
                    String licensePlate = scanner.nextLine();
                    rentalService.rentCar(customerId, licensePlate);
                    break;
                
                case 2:
                    System.out.println("Enter car license plate:");
                    licensePlate = scanner.nextLine();
                    rentalService.returnCar(licensePlate);
                    break;
                case 3:
                    System.out.println("All rentals:");
                    for (var rental : rentalService.getAllRentals()) {
                        System.out.println(rental);
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Oops, you chose the wrong option. Try again.");
            }
        }
        scanner.close();
    }
}
