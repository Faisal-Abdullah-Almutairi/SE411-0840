package edu.spu.se411.lab07_polymorphism.model;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import java.time.LocalDate;

public class FlightBooking extends Booking {
    private final double baseTicketPrice;
    private Double luggageWeight;

    public FlightBooking(String bookingId, String customerFullName, LocalDate travelDate,
                         String destinationCity, double baseTicketPrice)
            throws InvalidArgumentException {
        super(bookingId, customerFullName, travelDate, destinationCity);
        if (!Double.isFinite(baseTicketPrice) || baseTicketPrice < 0) {
            throw new InvalidArgumentException("Base ticket price must be nonnegative and finite.");
        }
        this.baseTicketPrice = baseTicketPrice;
    }

    public double getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public Double getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(double luggageWeight) throws InvalidArgumentException {
        if (!Double.isFinite(luggageWeight)
                || luggageWeight < GlobalConfiguration.MIN_LUGGAGE_WEIGHT
                || luggageWeight > GlobalConfiguration.MAX_LUGGAGE_WEIGHT) {
            throw new InvalidArgumentException("Luggage weight must be between 0 and 40 kg.");
        }
        this.luggageWeight = luggageWeight;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException {
        if (luggageWeight == null) {
            throw new MissingInformationException("Luggage weight has not been provided.");
        }
        return baseTicketPrice + luggageWeight * GlobalConfiguration.EXTRA_LUGGAGE_RATE;
    }
}
