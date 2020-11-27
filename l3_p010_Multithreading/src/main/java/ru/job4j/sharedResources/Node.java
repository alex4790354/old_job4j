package ru.job4j.sharedResources;

// NotThreadSafe imutable
public class Node<T> {

    private final Node next;
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

    public Node<T> setNext(Node next) {
        return new Node<>(next, this.value);
    }

    public Node<T> setValue(T value) {
        return new Node<>(this.next, value);
    }

}
