package job4j.p1_threads;

public class ConcurrentOutput {

    public static void main(String[] args) {
        Thread firstThread = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        firstThread.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }


}
