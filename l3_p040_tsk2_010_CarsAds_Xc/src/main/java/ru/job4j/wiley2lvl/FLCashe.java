package ru.job4j.wiley2lvl;

/**
 * @author   A_Vasiliev  9720560@gmail.com
 * @since    25.03.2018
 * @version  1.0.0
 */


public class FLCashe implements Comparable<FLCashe> {
   private int id;
   private double size;
   private long lastUsedT;

   public FLCashe(int id, double size, long lastUsedT) {
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
   public int compareTo(FLCashe o) {
      return Long.compare(this.lastUsedT, o.lastUsedT);
   }

   @Override
   public int hashCode() {
      int result = id * 31;
      return result;
   }


   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null || obj.getClass() != this.getClass()) {
         return false;
      }
      FLCashe cashe = (FLCashe) obj;
      return this.id == cashe.id;
   }


}

