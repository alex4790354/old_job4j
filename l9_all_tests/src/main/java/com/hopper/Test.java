package com.hopper;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static int factorial(int n) {
        int result = 1;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
            // or use result = n * factorial (n  - 1);
        }
        return result;
    }

    public static class Parentheses {
        int position;
        char openParenth;
        char closeParenth;

        public Parentheses(int position, char openParenth, char closeParenth) {
            this.position = position;
            this.openParenth = openParenth;
            this.closeParenth = closeParenth;
        }

        public int getPosition() {
            return position;
        }

        public char getOpenParenth() {
            return openParenth;
        }

        public char getCloseParenth() {
            return closeParenth;
        }
    }

    public static void main(String[] args) {
        // such as arrays,
        //, stacks, queues, hash-sets, hash-maps,
        //hash-tables, dictionaries, trees and binary trees,
        //heaps and graphs
        HashSet<String> map = new HashSet<String>();
        map.add("1");
        map.add("2");
        String str = "3";



        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.size();

        String testStrig = "[1(2)3]33(4)";
        List<Parentheses> openList = new ArrayList<Parentheses>();
        List<Parentheses> closeList = new ArrayList<Parentheses>();

        Pattern pattern = Pattern.compile("[(]");
        Matcher matcher = pattern.matcher(testStrig);
        while (matcher.find()) {
            openList.add(new Parentheses(matcher.start(), '(', ')'));
        }
        pattern = Pattern.compile("[)]");
        matcher = pattern.matcher(testStrig);
        while (matcher.find()) {
            closeList.add(new Parentheses(matcher.start(), '(', ')'));
        }

        pattern = Pattern.compile("[\\[]");
        matcher = pattern.matcher(testStrig);
        while (matcher.find()) {
            openList.add(new Parentheses(matcher.start(), '[', ']'));
        }
        pattern = Pattern.compile("[\\]]");
        matcher = pattern.matcher(testStrig);
        while (matcher.find()) {
            closeList.add(new Parentheses(matcher.start(), '[', ']'));
        }

        openList.sort(new Comparator<Parentheses>() {
            @Override
            public int compare(Parentheses o1, Parentheses o2) {
                return o2.position - o1.position;
            }
        }) ;
        closeList.sort(new Comparator<Parentheses>() {
            @Override
            public int compare(Parentheses o1, Parentheses o2) {
                return o1.position - o2.position;
            }
        }) ;

        List<Integer> validList = new ArrayList<Integer>();
        boolean foundPair = false;

        for (Parentheses parentheses : openList) {
            System.out.print(" " + parentheses.openParenth + parentheses.position + ", ");
        }
        System.out.println("Close");
        for (Parentheses parentheses : closeList) {
            System.out.print(" " + parentheses.position + parentheses.closeParenth + ", ");
        }
        System.out.println("Checking: ");

        for (int i = 0; i < openList.size(); i++) {
            foundPair = false;
            for (int k = 0; k < closeList.size(); k++) {
                // check the last one open parantheses:
                //if (i == 0) {
                    if (closeList.get(k).position > openList.get(i).position &&
                            !validList.contains(closeList.get(k).position) &&
                            !validList.contains(openList.get(i).position)) {
                        if (openList.get(i).closeParenth == closeList.get(k).closeParenth) {
                            foundPair = true;
                            validList.add(openList.get(i).position);
                            validList.add(closeList.get(k).position);
                            System.out.println("open: " + openList.get(i).position + ", close: " +
                                    closeList.get(k).position);
                            break;
                        } else {
                            break;
                        }
                    }
                //}
            }
            if (!foundPair) {
                break;
            }
        }

        if (foundPair) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }


    } //end of main
}

