package edu.spu.se411.lab08;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class DashboardObserver implements Observer {
    private final Map<String, Double> latestReadings = new LinkedHashMap<>();

    public Map<String, Double> getLatestReadings() {
        return Collections.unmodifiableMap(latestReadings);
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor sensor) {
            latestReadings.put(sensor.getName(), sensor.getReading());
            System.out.printf(
                    "Dashboard observer: %s reading changed to %.2f%n",
                    sensor.getName(), sensor.getReading());
        }
    }
}
