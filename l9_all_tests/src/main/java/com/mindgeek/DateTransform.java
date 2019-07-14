package com.mindgeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DateTransform {

    public static List<String> changeDateFormat(List<String> dates) {
        List<String> resulDates = new ArrayList<>();
        boolean included = false;
        String yyyy;
        String mm;
        String dd;


        for (int i = 0; i < dates.size(); i++) {
            included = false;

            // check if format: YYYY/MM/DD
            if (dates.get(i).length() > 8) {
                if (dates.get(i).substring(4, 5).equals("/") && dates.get(i).substring(7, 8).equals("/")) {
                    yyyy = dates.get(i).substring(0, 4);
                    mm = dates.get(i).substring(5, 7);
                    dd = dates.get(i).substring(8);
                    included = true;
                    resulDates.add(yyyy + mm + dd);
                }

                // check if format: DD/MM/YYYY
                // checking for (!included) - just save a little bit time
                if (!included && dates.get(i).substring(2, 3).equals("/") && dates.get(i).substring(5, 6).equals("/")) {
                    dd = dates.get(i).substring(0, 2);
                    mm = dates.get(i).substring(3, 5);
                    yyyy = dates.get(i).substring(6);
                    included = true;
                    resulDates.add(yyyy + mm + dd);
                }

                // check if format: MM-DD-YYYY
                if (!included && dates.get(i).substring(2, 3).equals("-") && dates.get(i).substring(5, 6).equals("-")) {
                    mm = dates.get(i).substring(0, 2);
                    yyyy = dates.get(i).substring(6);
                    dd = dates.get(i).substring(3, 5);
                    included = true;
                    resulDates.add(yyyy + mm + dd);
                }
            }
        }

        return resulDates;
    }

    public static void main(String[] args) {
        List<String> dates = changeDateFormat(Arrays.asList("2010/03/30", "15/12/2016", "11-15-2012", "20130720"));

        for(String date : dates) {
            System.out.println(date);
        }

    }

}
