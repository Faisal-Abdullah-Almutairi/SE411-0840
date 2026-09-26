package edu.spu.se411.lab07_polymorphism.model;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import java.time.LocalDate;

public class CarRentalBooking extends Booking {
    private final double dailyRentalRate;
    private Integer rentalDays;

    public CarRentalBooking(String bookingId, String customerFullName, LocalDate travelDate,
                            String destinationCity, double dailyRentalRate)
            throws InvalidArgumentException {
        super(bookingId, customerFullName, travelDate, destinationCity);
        if (!Double.isFinite(dailyRentalRate) || dailyRentalRate < 0) {
            throw new InvalidArgumentException("Daily rental rate must be nonnegative and finite.");
        }
        this.dailyRentalRate = dailyRentalRate;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) throws InvalidArgumentException {
        if (rentalDays < GlobalConfiguration.MIN_RENTAL_DAYS
                || rentalDays > GlobalConfiguration.MAX_RENTAL_DAYS) {
            throw new InvalidArgumentException("Rental days must be between 1 and 30.");
        }
        this.rentalDays = rentalDays;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException {
        if (rentalDays == null) {
            throw new MissingInformationException("Rental days have not been provided.");
        }
        return dailyRentalRate * rentalDays;
    }
}
