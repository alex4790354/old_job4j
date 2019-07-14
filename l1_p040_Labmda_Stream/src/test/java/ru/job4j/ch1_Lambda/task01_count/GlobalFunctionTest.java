package ru.job4j.ch1_Lambda.task01_count;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.log;
import static jdk.nashorn.internal.objects.NativeMath.round;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GlobalFunctionTest {

    /*@Test
    public void whenLinerFunctionThenLinerResult() {
        GlobalFunction globalFunction = new GlobalFunction();
        List<Double> result = globalFunction.diapason(2, 7, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(5.0D, 7.0D, 9.0D, 11.0D, 13.0D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenSquareResult() {
        GlobalFunction globalFunction = new GlobalFunction();
        List<Double> result = globalFunction.diapason(2, 7, x -> x * x);
        List<Double> expected = Arrays.asList(4.0D, 9.0D, 16.0D, 25.0D, 36.0D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLogResult() {
        GlobalFunction globalFunction = new GlobalFunction();
        List<Double> result = globalFunction.diapason(5, 8, x -> (double) ((int)(Math.log(x) * 100)) / 100);
        List<Double> expected = Arrays.asList(1.6D, 1.79D, 1.94D);
        assertThat(result, is(expected));
    }*/

}
