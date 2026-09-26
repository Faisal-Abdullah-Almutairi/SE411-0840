package edu.spu.se411.lab07_polymorphism;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.spu.se411.lab07_polymorphism.model.Booking;
import edu.spu.se411.lab07_polymorphism.model.CarRentalBooking;
import edu.spu.se411.lab07_polymorphism.model.FlightBooking;
import edu.spu.se411.lab07_polymorphism.model.SeatClass;
import edu.spu.se411.lab07_polymorphism.model.TrainBooking;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = createLogger();

    private static Logger createLogger() {
        try {
            Files.createDirectories(Path.of("logs", "App", "log4j"));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot create the logging directory.", e);
        }
        return LoggerFactory.getLogger(App.class);
    }

    public static double computeTotalPrice(Booking booking)
            throws MissingInformationException, InvalidArgumentException {
        return booking.calculateTotalPrice();
    }

    private static void showPrice(Booking booking) {
        try {
            System.out.printf("%s (%s): %.2f%n", booking.getBookingId(),
                    booking.getClass().getSimpleName(), computeTotalPrice(booking));
        } catch (MissingInformationException | InvalidArgumentException e) {
            LOGGER.error("Could not price booking " + booking.getBookingId(), e);
            System.out.println("Booking " + booking.getBookingId() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LOGGER.info("Application is starting...");
        try {
            LocalDate travelDate = LocalDate.of(2026, 10, 15);
            FlightBooking flight = new FlightBooking("F001", "Sara Ali", travelDate,
                    "Jeddah", 500.0);
            TrainBooking standardTrain = new TrainBooking("T001", "Omar Khalid", travelDate,
                    "Dammam", SeatClass.STANDARD);
            TrainBooking firstClassTrain = new TrainBooking("T002", "Mona Ahmed", travelDate,
                    "Makkah", SeatClass.FIRST_CLASS);
            CarRentalBooking car = new CarRentalBooking("C001", "Nora Salem", travelDate,
                    "Riyadh", 120.0);

            flight.setLuggageWeight(12.0);
            standardTrain.setDistanceKilometers(400.0);
            firstClassTrain.setDistanceKilometers(400.0);
            car.setRentalDays(3);

            Booking[] bookings = {flight, standardTrain, firstClassTrain, car};
            for (Booking booking : bookings) {
                showPrice(booking);
            }

            showPrice(new FlightBooking("F002", "Amal Hasan", travelDate, "Jeddah", 300.0));

            try {
                car.setRentalDays(31);
            } catch (InvalidArgumentException e) {
                LOGGER.error("Invalid input for booking " + car.getBookingId(), e);
                System.out.println("Booking " + car.getBookingId() + ": " + e.getMessage());
            }
        } catch (InvalidArgumentException e) {
            LOGGER.error("Could not create the booking demonstration", e);
            System.out.println("Could not create booking: " + e.getMessage());
        } finally {
            LOGGER.info("Application is stopping...");
        }
    }
}
