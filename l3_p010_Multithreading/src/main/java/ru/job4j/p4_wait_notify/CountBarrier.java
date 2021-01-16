package ru.job4j.p4_wait_notify;


public class CountBarrier {

    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() throws InterruptedException {
        synchronized (monitor) {
            System.out.println("count(): " + this.count++);
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            System.out.println("await start");

            try {
                while (this.count < this.total) {
                    System.out.println("await wait");
                    wait();
                }

            } catch (InterruptedException e) {
                System.out.println("await interrupted");
                Thread.currentThread().interrupt();
            }

            System.out.println("Yay!!! await() finished !!!");
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);

        Thread counter = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 10; i++) {
                            countBarrier.count();
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread awaiter = new Thread(
                countBarrier::await
        );

        counter.start();
        awaiter.start();

    }

}
