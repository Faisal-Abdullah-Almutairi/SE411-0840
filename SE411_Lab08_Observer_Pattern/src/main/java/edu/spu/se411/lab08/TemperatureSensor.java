package edu.spu.se411.lab08;

public final class TemperatureSensor extends Sensor {
    public TemperatureSensor() {
        super("Temperature");
    }

    @Override
    public TemperatureSensor clone() {
        return (TemperatureSensor) super.clone();
    }
}
