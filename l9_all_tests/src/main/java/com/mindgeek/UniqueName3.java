package com.mindgeek;

import java.util.*;

public class UniqueName3 {

    public static void main(String[] args) {
        String[] testArray = {"Anna", "Bonya", "Roza", "Anna"};
        Map<String, Integer> testMap = new HashMap<String, Integer>();
        testMap.put("Anna", 1);
        for (int i = 1; i < testArray.length; i++) {
            if (testMap.containsKey(testArray[i])) {
                testMap.replace(testArray[i], testMap.get(testArray[i]) + 1);
            } else {
                testMap.put(testArray[i], 1);
            }
        }

        for (String k : testMap.keySet()) {
            System.out.println("key: " + k + ", Value: " + testMap.get(k));
        }

        testMap.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue())
        .forEach(System.out::println); // или любой другой конечный метод

    }

}
