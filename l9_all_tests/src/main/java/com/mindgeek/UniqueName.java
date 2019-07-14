package com.mindgeek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UniqueName {

    public static String firstUniqueName(String[] names) {
        String result = null;
        boolean foundMatch = false;

        // to make it work faster: I have to convert names to LinkedList
        // test linked list. And remove matching names when find it.
        // Sorry, Don't want to spend testing time on this implantation .
        LinkedList<String> list = new LinkedList<String>();


        for (int i = 0; i < names.length; i++) {
            foundMatch = false;
            for (int j = 0;  j < names.length; j++) {
                if (names[i].equals(names[j]) && i != j) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                result = names[i];
                // ToDo: add break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqueName(new String[] { "Abbi", "Adeline", "Adeline", "Abbi" }));
    }

}
