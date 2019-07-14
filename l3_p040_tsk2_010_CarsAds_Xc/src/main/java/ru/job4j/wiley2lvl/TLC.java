package ru.job4j.wiley2lvl;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * @author   A_Vasiliev  9720560@gmail.com
 * @since    25.03.2018
 * @version  1.0.0
 */

public class TLC<T> {
   private String strategy;
   TLCLazy<T> tlcLazy = null;
   TLCTime<T> tlcTime = null;

   public TLC(String strategy, double secLMaxSize, double fstLMaxSize) throws IOException, ClassNotFoundException {
      this.strategy = strategy;
      if (this.strategy.equals("Lazy")) {
         this.tlcLazy = new TLCLazy<T>(secLMaxSize);
      } else if (this.strategy.equals("Time")) {
         this.tlcTime = new TLCTime<T>(secLMaxSize, fstLMaxSize);
      }
   }

   public SoftReference<T> get(int casheID) throws IOException, ClassNotFoundException {
      SoftReference<T> softObj = null;
      if (this.strategy.equals("Lazy")) {
         softObj = this.tlcLazy.get(casheID);
      } else if (this.strategy.equals("Time")) {
         softObj = this.tlcTime.get(casheID);
      }
      return softObj;
   }


   public boolean addObj(T obj) throws IOException {
      boolean result = false;
      if (this.strategy.equals("Lazy")) {
         result = this.tlcLazy.addObj(obj);
      } else if (this.strategy.equals("Time")) {
         result = this.tlcTime.addObj(obj);
      }
      return result;
   }


   public boolean remObj(T obj) throws IOException, ClassNotFoundException {
      boolean result = false;
      if (this.strategy.equals("Lazy")) {
         result = this.tlcLazy.remObj(obj);
      } else if (this.strategy.equals("Time")) {
         result = this.tlcTime.remObj(obj);
      }
      return result;
   }


   public int getDataSize() {
      int result = -1;
      if (this.strategy.equals("Lazy")) {
         result = this.tlcLazy.getDataSize();
      } else if (this.strategy.equals("Time")) {
         result = this.tlcTime.getDataSize();
      }
      return result;
   }

}
