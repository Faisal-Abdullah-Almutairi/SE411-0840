package edu.spu.se411.lab08;

public final class LoggerObserver implements Observer {
    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor sensor) {
            System.out.printf(
                    "Logger observer: %s reading changed to %.2f%n",
                    sensor.getName(), sensor.getReading());
        }
    }
}
