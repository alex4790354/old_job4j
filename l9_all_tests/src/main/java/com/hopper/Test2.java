package com.hopper;

import java.util.*;

public class Test2 {

    public static void main(String[] args) {
        System.out.println("Yes");
        int n = 6;
        int m = 3;

        try {
            int a = 0;
        } catch (InputMismatchException e) {
            System.out.println("1");
        } catch (ArithmeticException e) {
            System.out.println("2");
        }


        Deque<Integer> deque = new ArrayDeque<Integer>();

        /*while () {

        }*/
        Deque<Integer> testDeque = new ArrayDeque<Integer>();
        Set<Integer> testSet = new HashSet<Integer>();
        int maxCount = Integer.MIN_VALUE;

        int x, y, num;
        int[] arr = {5, 3, 5, 2, 3, 2};

        for (int i = 0; i < arr.length; i++) {
            //num = in.nextInt();
            num = arr[i];
            if (i < m) {
                testDeque.add(num);
                testSet.add(num);
            } else {
                deque.add(num);
            }

        }
        maxCount = testSet.size();
        System.out.println("Here: " + maxCount + ", " + testDeque.size() + ", " + deque.size());

        while (deque.size() > 0 ) {
            x = testDeque.pollFirst();
            y = deque.pollFirst();
            testDeque.addLast(y);

            if (!testDeque.contains(x)) {
                testSet.remove(x);
            }
            testSet.add(y);

            if (testSet.size() > maxCount) {
                maxCount = testSet.size();
            }

        }
        System.out.println(maxCount);



        System.out.println(maxCount);



    }
}
