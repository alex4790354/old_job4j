package ru.job4j.p3_synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    public static void main(String[] args) {
        System.out.println("Hey");
    }

    @GuardedBy("this")
    private List<T> lockedList;

    public SingleLockList() {
        this.lockedList = new ArrayList<T>();
    }

    public synchronized void add(T value) {
        this.lockedList.add(value);
    }

    public synchronized T get(int index) {
        return lockedList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return this.lockedList.iterator();
    }
}
