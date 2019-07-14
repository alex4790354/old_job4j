package ru.job4j.wiley;

import java.io.Serializable;

public class MyUser implements Serializable {

   private int num1;
   private int lastTimeUsed;

   public MyUser(int fileSize, int lastTimeUsed) {
      this.num1 = fileSize;
      this.lastTimeUsed = lastTimeUsed;
   }

   public int getNum1() {
      return num1;
   }

   public void setNum1(int num1) {
      this.num1 = num1;
   }

   public int getLastTimeUsed() {
      return lastTimeUsed;
   }

   public void setLastTimeUsed(int lastTimeUsed) {
      this.lastTimeUsed = lastTimeUsed;
   }
}
