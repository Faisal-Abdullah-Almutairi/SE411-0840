package edu.spu.se411.lab07_polymorphism.model;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import java.time.LocalDate;

public abstract class Booking {
    private final String bookingId;
    private final String customerFullName;
    private final LocalDate travelDate;
    private final String destinationCity;

    protected Booking(String bookingId, String customerFullName, LocalDate travelDate,
                      String destinationCity) {
        this.bookingId = bookingId;
        this.customerFullName = customerFullName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public abstract double calculateTotalPrice()
            throws MissingInformationException, InvalidArgumentException;
}
