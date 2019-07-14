package com.mindgeek;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UniqueName2 {
    static List<String> nameList = new ArrayList<>();

    public static String firstUniqueName(String[] names) {
        String result = null;
        boolean foundMatch = false;

        for (String name : names) {
            nameList.add(name);
        }

        for (String name : nameList) {
            System.out.println("-> " + name);
        }

        int listLenght = nameList.size();
        boolean foundOne = false;
        boolean removedOne = false;
        while (listLenght > 1 && !foundOne) {
            for (int i = 0; i < listLenght; i++) {
                removedOne = false;
                for (int j = i + 1; j < listLenght; j++) {
                    if (nameList.get(i).equals(nameList.get(j))) {
                        String removeThis = nameList.get(i);

                        ListIterator<String> iter = nameList.listIterator();
                        while(iter.hasNext()){
                            if(iter.next().equals(removeThis)){
                                iter.remove();
                            }
                        }

                        System.out.println("i = " + i + ", j = " + j);
                        for (String name : nameList) {
                            System.out.println("==>> " + name);
                        }

                        removedOne = true;
                        listLenght = nameList.size();
                        break;
                    }
                    if (j == listLenght - 1) {
                        foundOne = true;
                        result = nameList.get(i);
                    }
                }
                if (removedOne || foundOne) {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Result: " + firstUniqueName(new String[] { "Abbi", "Adeline", "Adeline", "Abbi" , "Jhon", "Rozi", "Rozi"}));
        for (String name : nameList) {
            System.out.println(name);
        }
    }

}
