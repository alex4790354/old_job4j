package ru.job4j.p5_NonBlockingAlgoritm;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void when3IncrementAndGet() {
        CASCount count = new CASCount();
        count.increment();
        assertThat(count.getCount(), is(1));
        count.increment();
        assertThat(count.getCount(), is(2));
        count.increment();
        assertThat(count.getCount(), is(3));
        assertThat(count.getCount(), is(3));
    }

}