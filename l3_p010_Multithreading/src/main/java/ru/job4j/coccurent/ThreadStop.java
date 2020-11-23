package ru.job4j.coccurent;

class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int index = 0;
        try {

            while (!Thread.currentThread().isInterrupted()) {
                index++;
                Thread.sleep(100);
                if (index % 4 == 0)
                    System.out.print("\rLoading : -");
                else if (index % 4 == 1)
                    System.out.print("\rLoading : /");
                else if (index % 4 == 2)
                    System.out.print("\rLoading : |");
                else if (index % 4 == 3)
                    System.out.print("\rLoading : \\");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


public class ThreadStop {

    public static void main(String[] args) throws InterruptedException {

        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt(); //


    }

}
