package ru.job4j.p3_synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.copy;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    public static void main(String[] args) {
        System.out.println("Hey");
    }

    @GuardedBy("this")
    private List<T> lockedList;

    public SingleLockList() {
        this.lockedList = new ArrayList<>();
    }

    public synchronized void add(T value) {
        this.lockedList.add(value);
    }

    public synchronized T get(int index) {
        return lockedList.get(index);
    }

    public synchronized List<T> copySingleLockList() {
        List<T> copy = new ArrayList<>();
        for (T value : this.lockedList) {
            copy.add(value);
        }
        return copy;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        //return new ArrayList<>(this.lockedList).iterator();
        // or we can use:
        List<T> newList = new ArrayList<>();
        copy(newList, this.lockedList);
        return newList.iterator();
    }
}
