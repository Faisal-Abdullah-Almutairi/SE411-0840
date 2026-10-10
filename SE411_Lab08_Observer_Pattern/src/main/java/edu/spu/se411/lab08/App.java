package edu.spu.se411.lab08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {
    private static Logger logger;

    private App() {
    }

    public static void main(String[] args) {
        configureLogging();
        logger.info("Application is starting...");

        Sensor temperature = new TemperatureSensor();
        Sensor humidity = new HumiditySensor();

        Observer dashboard = new DashboardObserver();
        Observer loggerObserver = new LoggerObserver();

        temperature.register(dashboard);
        temperature.register(loggerObserver);
        humidity.register(dashboard);
        humidity.register(loggerObserver);

        Random random = new Random();
        int iteration = 0;

        while (iteration < 10) {
            temperature.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);
            iteration++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                logger.error("Sensor simulation was interrupted.", exception);
                break;
            }
        }

        TemperatureSensor clonedTemperature = ((TemperatureSensor) temperature).clone();
        logger.info("Cloned sensor observer count: {}", clonedTemperature.getObserverCount());
        clonedTemperature.setReading(25.0);

        logger.info("Application has finished.");
    }

    private static void configureLogging() {
        try {
            Files.createDirectories(Path.of("logs", "App", "log4j"));
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to create the logging directory.", exception);
        }
        logger = LoggerFactory.getLogger(App.class);
    }
}
