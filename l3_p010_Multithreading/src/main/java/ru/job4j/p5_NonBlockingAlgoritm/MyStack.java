package ru.job4j.p5_NonBlockingAlgoritm;

public class MyStack<T> {

    private Node<T> head;

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        if (head == null) {
            head = temp;
            return;
        }
        temp.next = head;
        head = temp;
    }

    public T poll() {
        Node<T> temp = head;
        if (temp == null) {
            throw new IllegalStateException("Stack is empty");
        }
        head = temp.next;
        temp.next = null;
        return temp.value;
    }


}
