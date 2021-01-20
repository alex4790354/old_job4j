package ru.job4j.p5_NonBlockingAlgoritm;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;


@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        this.count.set(0);
    }

    public void increment() {
        int temp;
        Integer current;
        do {
            current = count.get();
            temp = current + 1;
        } while (!count.compareAndSet(current, temp));
    }

    public int getCount() {
        return count.get();
    }

}
