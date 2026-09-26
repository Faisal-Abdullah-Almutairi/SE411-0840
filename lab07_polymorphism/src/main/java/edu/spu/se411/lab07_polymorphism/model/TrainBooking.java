package edu.spu.se411.lab07_polymorphism.model;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import java.time.LocalDate;

public class TrainBooking extends Booking {
    private final SeatClass seatClass;
    private Double distanceKilometers;

    public TrainBooking(String bookingId, String customerFullName, LocalDate travelDate,
                        String destinationCity, SeatClass seatClass) throws InvalidArgumentException {
        super(bookingId, customerFullName, travelDate, destinationCity);
        if (seatClass == null) {
            throw new InvalidArgumentException("Seat class must be STANDARD or FIRST_CLASS.");
        }
        this.seatClass = seatClass;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public Double getDistanceKilometers() {
        return distanceKilometers;
    }

    public void setDistanceKilometers(double distanceKilometers) throws InvalidArgumentException {
        if (!Double.isFinite(distanceKilometers)
                || distanceKilometers < GlobalConfiguration.MIN_TRAIN_DISTANCE
                || distanceKilometers > GlobalConfiguration.MAX_TRAIN_DISTANCE) {
            throw new InvalidArgumentException("Distance must be between 1 and 2000 km.");
        }
        this.distanceKilometers = distanceKilometers;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException {
        if (distanceKilometers == null) {
            throw new MissingInformationException("Train distance has not been provided.");
        }
        double rate = seatClass == SeatClass.STANDARD
                ? GlobalConfiguration.TRAIN_STANDARD_RATE
                : GlobalConfiguration.TRAIN_FIRST_CLASS_RATE;
        return distanceKilometers * rate;
    }
}
