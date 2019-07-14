package ru.job4j.ch1_Lambda.task01_count;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GlobalFunction {
    /**
     * Calculate digits row with any functions
     *
     * @param start start
     * @param end end
     * @param func your lambda function
     * @return result
     */
    List<Double> diapason(int start, int end, java.util.function.Function<Double, Double> func) {
        List<Double> range = new ArrayList<>();
        for (int i = start; i < end; i++) {
            range.add(func.apply((double) i));
        }
        return range;
    }

}


