package ru.job4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test01 {

    int[] testArr = new int[5];
    List<Integer> list = new ArrayList<Integer>();
    int length = list.size();

    public static void main(String[] args) {
        int arr[] = new int[5];
        int $1234 = 5;
        arr[0] = 8;
        arr[1] = 10;
        arr[2] = 64;
        arr[3] = 512;
        arr[4] = 4096;
        int checkNum = arr.length;

        int maxNum = 1;

        for (int testNum = arr[0]; testNum > 1; testNum--) {

            for (int i = 0; i < 5; i++) {
                if (arr[i] % testNum != 0) {
                    break;
                }
                if (i == 4) {
                    System.out.println("first loop: " + testNum);
                    maxNum = testNum;
                }
            }
            if (maxNum > 1) {
                System.out.println("Second loop: " + maxNum);
                break;
            }
        }

        System.out.println(maxNum);

    }


}
