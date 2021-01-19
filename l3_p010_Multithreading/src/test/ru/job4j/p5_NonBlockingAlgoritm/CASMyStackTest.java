package ru.job4j.p5_NonBlockingAlgoritm;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Stack;

public class CASMyStackTest {
    @Test
    public void when3PushThen3Poll() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

}