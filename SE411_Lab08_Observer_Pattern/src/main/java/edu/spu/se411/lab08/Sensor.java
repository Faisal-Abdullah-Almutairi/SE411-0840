package edu.spu.se411.lab08;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Sensor implements Subject, Cloneable {
    private final String name;
    private double reading;
    private List<Observer> observers;

    protected Sensor(String name) {
        this.name = Objects.requireNonNull(name);
        reading = Double.NaN;
        observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        if (Double.compare(this.reading, reading) != 0) {
            this.reading = reading;
            notifyObservers();
        }
    }

    public int getObserverCount() {
        return observers.size();
    }

    @Override
    public void register(Observer observer) {
        Objects.requireNonNull(observer);
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : List.copyOf(observers)) {
            observer.update(this);
        }
    }

    @Override
    public Sensor clone() {
        try {
            Sensor clone = (Sensor) super.clone();
            clone.observers = new ArrayList<>();
            return clone;
        } catch (CloneNotSupportedException exception) {
            throw new AssertionError(exception);
        }
    }
}
