package ru.job4j.p4_wait_notify;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    static final int QUEUE_LIMIT = 4;

    public void offer(T value) {
        synchronized (this.queue) {
            try {
                while (queue.size() > QUEUE_LIMIT) {
                    //System.out.println("offer -> wait mode");
                    this.queue.wait();
                }
                //System.out.println("offer: " + value);
                this.queue.offer(value);
                this.queue.notify();

            } catch (InterruptedException e) {
                System.out.println("offer interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    public T poll() {
        T result = null;
        synchronized (this.queue) {
            try {
                while (queue.size() == 0) {
                    //System.out.println("poll -> wait mode");
                    this.queue.wait();
                }
                result = this.queue.poll();
                //System.out.println("poll(): " + result);
                this.queue.notify();

            } catch (InterruptedException e) {
                System.out.println("poll interrupted");
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> blockingQueue = new SimpleBlockingQueue<>();

        Thread offerThread = new Thread(
                () -> {
                    try {
                        Thread.sleep(2000);
                        for (int i = 0; i < 10; i++) {
                            blockingQueue.offer(i);
                            Thread.sleep(250);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread pollThread = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 10; i++) {
                            blockingQueue.poll();
                            Thread.sleep(600);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        pollThread.start();
        offerThread.start();

    }

}
