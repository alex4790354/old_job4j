package ru.job4j.p4_wait_notify;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void checkSimpleBlockingQueueTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();

        Thread offerThread = new Thread(
                () -> {
                    try {
                        Thread.sleep(2000);
                        for (int i = 0; i < 10; i++) {
                            simpleBlockingQueue.offer(i);
                            Thread.sleep(200);
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
                            simpleBlockingQueue.poll();
                            Thread.sleep(250);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        offerThread.start();
        pollThread.start();
        System.out.println("Check-1");
        assertTrue(offerThread.isAlive());
        assertTrue(pollThread.isAlive());

        offerThread.join();
        System.out.println("Check-2");
        assertFalse(offerThread.isAlive());
        assertTrue(pollThread.isAlive());

        pollThread.join();
        System.out.println("Check-3");
        assertFalse(offerThread.isAlive());
        assertFalse(pollThread.isAlive());
    }

}