package ru.job4j.p3_synchronization;

public class Buffer {

    private StringBuilder buffer = new StringBuilder();

    // object to monitor: 'this' = method = block (?)
    // Critical section: line 10 - 11
    public synchronized void add(int value) {
        System.out.print(value);
        buffer.append(value);
    }

    // object to monitor: 'this' = method = block (?)
    // Critical section: line 18
    @Override
    public synchronized String toString() {
        return buffer.toString();
    }

}
