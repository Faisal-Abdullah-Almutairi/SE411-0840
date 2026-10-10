package edu.spu.se411.lab08;

public interface Subject {
    /** Registers a new observer. */
    void register(Observer observer);

    /** Unregisters an observer. */
    void unregister(Observer observer);

    /** Notifies all registered observers of data change. */
    void notifyObservers();
}
