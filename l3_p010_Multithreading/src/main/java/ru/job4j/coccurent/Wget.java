package ru.job4j.coccurent;

public class Wget {

    public static void main(String[] args) {

        Thread thread = new Thread(
            () -> {
                try {
                    for (int index=0; index < 101; index++) {
                        Thread.sleep(100);
                        System.out.print("\rLoading : " + index  + "%");
                    }
                    System.out.print("\rLoading done.");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        );

        thread.start();


    }


}
