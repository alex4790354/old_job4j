package ru.job4j.wiley;

/**
 * single cashe to store objects
 */


public class FirstLvlCashe implements Comparable<FirstLvlCashe> {
   private int id;
   private double size;
   private long lastUsedT;

   public FirstLvlCashe(int id, double size, long lastUsedT) {
      this.id = id;
      this.size = size;
      this.lastUsedT = lastUsedT;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public double getSize() {
      return size;
   }

   public void setSize(double size) {
      this.size = size;
   }

   public long getLastUserdT() {
      return lastUsedT;
   }

   public void setLastUserdT(long lastUserdT) {
      this.lastUsedT = lastUserdT;
   }

   @Override
   public int compareTo(FirstLvlCashe o) {
      int result = 0;
      if (this.lastUsedT > o.lastUsedT) {
         result = 1;
      }
      if (this.lastUsedT < o.lastUsedT) {
         result = -1;
      }
      return result;
   }
}
