package ru.job4j.wiley2lvl;

import java.io.Serializable;


   /**
    * @author   A_Vasiliev  9720560@gmail.com
    * @since    25.03.2018
    * @version  1.0.0
    */

public class MyObject implements Serializable {
   private int num1;
   private String str;


   public MyObject(int num1, String str) {
      this.num1 = num1;
      this.str = str;
   }

   public int getNum1() {
      return num1;
   }

   public void setNum1(int num1) {
      this.num1 = num1;
   }

   public String getStr() {
      return str;
   }

   public void setStr(String str) {
      this.str = str;
   }

      @Override
      public int hashCode() {
         int result = num1 * 31;
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
         MyObject myObject = (MyObject) obj;
         return (this.num1 == myObject.num1 && this.str.equals(myObject.str));
      }


}

