package ru.job4j.p11_ThreadState;

public class ThreadState {

    public static void main(String[] args) {

        Thread first = new Thread(
                () -> {}
        );
        System.out.println("First: " + first.getState());
        first.start();

        Thread second = new Thread(
                () -> {}
        );
        System.out.println("Second: " + second.getState());
        second.start();


        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println("In loop. first.getState:" + first.getState() + ", second.getState: " + second.getState());
        }

        System.out.println("Last. first.getState: " + first.getState() + ", second.getState: " + second.getState());

    }

}
