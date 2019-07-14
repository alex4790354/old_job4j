package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortArray {

    public static void main(String[] args) {
        List<String> primeList = new ArrayList<String>();
        List<String> otherwiseList = new ArrayList<String>();
        List<String> inputOrders = Arrays.asList(
                "[zld 93 12]",
                "[zzz kindle bool]",
                "[aaa kindle aool]",
                "[10a echo show]",
                "[17g 12 25]",
                "[ccc kindle bool]",
                "[125 echo dot]"
        );

        List<String> outOders = Arrays.asList(
                "[125 echo dot]",
                "[10a echo show]",
                "[ab1 kindle book]",
                "[afp kindle book]",
                "[zld 93 12]",
                "[17g 12 25]"
        );
        String[] testLineArrya;

        for (String inputLine : inputOrders) {
            testLineArrya = inputLine.substring(1, inputLine.length() - 1).split(" ");
            if (testLineArrya[1].matches("\\d+")) {
                otherwiseList.add(inputLine);
            } else {
                primeList.add(inputLine);
            }
        }

        primeList.sort(new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                int result = left.compareTo(right);
                String[] leftArr = left.split(" ");
                String[] rightArr = right.split(" ");
                for (int indx = 1; indx < leftArr.length; indx++) {
                    result = leftArr[indx].compareTo(rightArr[indx]);
                    if (result != 0) {
                        if (indx > 1) {
                            System.out.println(left + " -- " + right + ". Indx = " + indx + ", result = " + result);
                        }
                        break;
                    }
                }
                if (result == 0) {
                    result = leftArr[0].compareTo(rightArr[0]);
                }
                return result;
            }
        });

        primeList.addAll(otherwiseList);
        for (String testStr : primeList) {
            System.out.println(testStr);
        }
    }

}
