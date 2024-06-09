package services;

import models.Car;
import models.Customer;
import models.Rental;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalService {
    private List<Rental> rentals;
    private CarService carService;
    private CustomerService customerService;

    public RentalService(CarService carService, CustomerService customerService) {
        this.rentals = new ArrayList<>();
        this.carService = carService;
        this.customerService = customerService;
    }

    public void rentCar(String customerId, String licensePlate) {
        Car car = carService.getCarByLicensePlate(licensePlate);
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null && car != null && car.isAvailable()) {
            Rental rental = new Rental(car, customer, new Date());
            rentals.add(rental);
            car.setAvailable(false);
            System.out.println("Car rented successfully to " + customer.getName());
        } else {
            System.out.println("Car rental failed. Either car or customer not found, or car not available.");
        }
    }

    public void returnCar(String licensePlate) {
        for (Rental rental : rentals) {
            if (rental.getCar().getLicensePlate().equals(licensePlate) && rental.getReturnDate() == null) {
                rental.setReturnDate(new Date());
                rental.getCar().setAvailable(true);
                System.out.println("Car returned successfully.");
                return;
            }
        }
        System.out.println("Car return failed. Rental not found.");
    }

    public List<Rental> getAllRentals() {
        return rentals;
    }
}
