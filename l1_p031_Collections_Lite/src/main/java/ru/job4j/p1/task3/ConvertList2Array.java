package ru.job4j.p1.task3;

import java.util.List;

public class ConvertList2Array {
   public int[][] toArray(List<Integer> list, int rows) {
      int cells = list.size() / rows;
      if (cells * rows < list.size()) {
         cells++;
      }
      int[][] array = new int[rows][cells];
      int curIndx;

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cells; j++) {
            curIndx = cells * i + j ;
            if (curIndx < list.size()) {
               array[i][j] = list.get(curIndx);
            } else {
               break;
            }
         }
      }

      return array;
   }
}