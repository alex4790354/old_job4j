package ru.job4j.p4_wait_notify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

public class ParallelSearch {

    public static void main(String[] args) {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();

        final Thread consumer = new Thread(
                () -> {
                    synchronized (queue) {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                if (queue.isEmpty()) {
                                    queue.wait();
                                    System.out.println("queue - wait");
                                }
                                System.out.println(queue.poll());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                        System.out.println("Consumer - quite");
                    }
                }
        );

        final Thread producer = new Thread(
                () -> {
                    synchronized (queue) {
                        for (int index = 0; index != 3; index++) {
                            queue.offer(index);
                            queue.notify();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        producer.start();
        consumer.start();

        while (consumer.isAlive()) {
            if (!producer.isAlive()) {
                consumer.interrupt();
            }
        }
    }
}
