package ru.job4j.p5_NonBlockingAlgoritm;

public class Node<T> {
    final T value;

    Node<T> next;

    public Node(final T value) {
        this.value = value;
    }
}
