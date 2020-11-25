package ru.job4j.coccurent;

public class ThreadStop2 {

    public static void main(String[] args) throws InterruptedException {

        Thread progress = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        count++;
                        try {
                            System.out.println("start ..." + count);
                            Thread.sleep(9000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().isInterrupted());
                            Thread.currentThread().interrupt();
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
        System.out.println("main and progress are joined");
    }

}
