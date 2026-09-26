package edu.spu.se411.lab07_polymorphism;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.spu.se411.lab07_polymorphism.model.Booking;
import edu.spu.se411.lab07_polymorphism.model.CarRentalBooking;
import edu.spu.se411.lab07_polymorphism.model.FlightBooking;
import edu.spu.se411.lab07_polymorphism.model.SeatClass;
import edu.spu.se411.lab07_polymorphism.model.TrainBooking;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingTest {
    private static final LocalDate DATE = LocalDate.of(2026, 10, 15);

    @Test
    void calculatesFlightPriceAndChecksLuggageBounds() throws Exception {
        FlightBooking flight = new FlightBooking("F1", "Sara Ali", DATE, "Jeddah", 500.0);
        assertThrows(MissingInformationException.class, () -> App.computeTotalPrice(flight));
        flight.setLuggageWeight(0.0);
        assertEquals(500.0, App.computeTotalPrice(flight));
        flight.setLuggageWeight(GlobalConfiguration.MAX_LUGGAGE_WEIGHT);
        assertEquals(500.0 + 40.0 * GlobalConfiguration.EXTRA_LUGGAGE_RATE,
                App.computeTotalPrice(flight));
        assertThrows(InvalidArgumentException.class, () -> flight.setLuggageWeight(-1.0));
        assertThrows(InvalidArgumentException.class, () -> flight.setLuggageWeight(40.1));
        assertThrows(InvalidArgumentException.class, () -> flight.setLuggageWeight(Double.NaN));
        assertEquals(40.0, flight.getLuggageWeight().doubleValue());
    }

    @Test
    void calculatesBothTrainClassesAndChecksDistanceBounds() throws Exception {
        TrainBooking standard = new TrainBooking("T1", "Omar Ali", DATE, "Dammam",
                SeatClass.STANDARD);
        TrainBooking firstClass = new TrainBooking("T2", "Mona Ali", DATE, "Dammam",
                SeatClass.FIRST_CLASS);
        assertThrows(MissingInformationException.class, () -> App.computeTotalPrice(standard));
        standard.setDistanceKilometers(1.0);
        firstClass.setDistanceKilometers(2000.0);
        assertEquals(GlobalConfiguration.TRAIN_STANDARD_RATE, App.computeTotalPrice(standard));
        assertEquals(2000.0 * GlobalConfiguration.TRAIN_FIRST_CLASS_RATE,
                App.computeTotalPrice(firstClass));
        assertThrows(InvalidArgumentException.class, () -> standard.setDistanceKilometers(0.0));
        assertThrows(InvalidArgumentException.class, () -> standard.setDistanceKilometers(2001.0));
        assertThrows(InvalidArgumentException.class,
                () -> firstClass.setDistanceKilometers(Double.POSITIVE_INFINITY));
    }

    @Test
    void calculatesRentalPriceAndChecksDayBounds() throws Exception {
        CarRentalBooking car = new CarRentalBooking("C1", "Nora Ali", DATE, "Riyadh", 120.0);
        assertThrows(MissingInformationException.class, () -> App.computeTotalPrice(car));
        car.setRentalDays(1);
        assertEquals(120.0, App.computeTotalPrice(car));
        car.setRentalDays(30);
        assertEquals(3600.0, App.computeTotalPrice(car));
        assertThrows(InvalidArgumentException.class, () -> car.setRentalDays(0));
        assertThrows(InvalidArgumentException.class, () -> car.setRentalDays(31));
        assertEquals(30, car.getRentalDays().intValue());
    }

    @Test
    void usesPolymorphismAndValidatesCreation() throws Exception {
        FlightBooking flight = new FlightBooking("F1", "Sara Ali", DATE, "Jeddah", 500.0);
        TrainBooking train = new TrainBooking("T1", "Omar Ali", DATE, "Dammam",
                SeatClass.STANDARD);
        CarRentalBooking car = new CarRentalBooking("C1", "Nora Ali", DATE, "Riyadh", 120.0);
        flight.setLuggageWeight(2.0);
        train.setDistanceKilometers(10.0);
        car.setRentalDays(2);
        Booking[] bookings = {flight, train, car};
        assertEquals(500.0 + 2.0 * GlobalConfiguration.EXTRA_LUGGAGE_RATE,
                App.computeTotalPrice(bookings[0]));
        assertEquals(10.0 * GlobalConfiguration.TRAIN_STANDARD_RATE,
                App.computeTotalPrice(bookings[1]));
        assertEquals(240.0, App.computeTotalPrice(bookings[2]));
        assertEquals("Sara Ali", bookings[0].getCustomerFullName());
        assertThrows(InvalidArgumentException.class,
                () -> new TrainBooking("T3", "Name", DATE, "Jeddah", null));
        assertThrows(InvalidArgumentException.class,
                () -> new FlightBooking("F3", "Name", DATE, "Jeddah", -1.0));
        assertThrows(InvalidArgumentException.class,
                () -> new CarRentalBooking("C3", "Name", DATE, "Jeddah", -1.0));
    }
}
