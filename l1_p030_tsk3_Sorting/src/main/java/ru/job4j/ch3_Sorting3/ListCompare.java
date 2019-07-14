package ru.job4j.ch3_Sorting3;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {

   @Override
   public int compare(String left, String right) {
      int result = 0;
      int lenght = left.length() > right.length() ? right.length() : left.length();
      for (int i = 0; i < lenght; i++) {
         result = Character.compare(left.charAt(i), right.charAt(i));
         if (result != 0)
            break;
      }
      if (result == 0) {
         result = left.length() - right.length();
      }
      return result;
   }
}
