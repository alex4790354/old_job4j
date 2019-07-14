package com.mindgeek;

import java.util.*;

public class Rare {
    static List<NumPlusFreq> myList = new ArrayList<>();

    static class NumPlusFreq {
        int numb;
        int frequency;

        public NumPlusFreq(int numb) {
            this.numb = numb;
            this.frequency = 1;
        }

        /*public NumPlusFreq(NumPlusFreq oldNumb) {
            this.frequency = oldNumb.frequency + 1;
        }*/
    }

    private static void addNumb(int newNum) {
        boolean foundOne = false;

        for (NumPlusFreq numPlusFreq : myList) {
            if (numPlusFreq.numb == newNum) {
                //JustNumb newJustNumb = new
                numPlusFreq.frequency = numPlusFreq.frequency + 1;
                foundOne = true;
                break;
            }
        }
        if (!foundOne) {
            myList.add(new NumPlusFreq(newNum));
        }
    }

    public Rare(int[] arr) {
        myList.add(new NumPlusFreq(arr[0]));
        for (int i = 1; i < arr.length; i++) {
            addNumb(arr[i]);
        }
    }


    public static int nthMostRare(int[] elements, int n) {
        int result = 0;
        for (int element : elements) {
            addNumb(element);
        }

        myList.sort(new Comparator<NumPlusFreq>() {
            @Override
            public int compare(NumPlusFreq left, NumPlusFreq right) {
                return right.frequency - left.frequency;
            }
        });

        return myList.get(n - 1).numb;
    }


    public static void main(String[] args) {
        int x = nthMostRare(new int[] { 5, 4, 3, 2, 1, 5, 4, 3, 2, 5, 4, 3, 5, 4, 5 }, 2);

        //int[] arr = {5, 4, 6, 4, 6};
        //Rare rare = new Rare(arr);

        /*for (NumPlusFreq numPlusFreq : myList) {
            System.out.println("Num: " + numPlusFreq.numb + ", Freq: " + numPlusFreq.frequency);
        }*/

        for (NumPlusFreq numPlusFreq : myList) {
            System.out.println("Num: " + numPlusFreq.numb + ", Freq: " + numPlusFreq.frequency);
        }

        System.out.println(String.format("And x = %s", x));

    }



}
