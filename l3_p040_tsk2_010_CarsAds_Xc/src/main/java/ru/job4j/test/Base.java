package ru.job4j.test;

public class Base {

   void print() {
      System.out.println("1");
   }

}

class Child extends Base {
   @Override
   void print() {
      System.out.println(myFunc());
   }


   public static void main(String[] args) {
      Base obj = new Child();
      obj.print();
   }

   public int myFunc() {
      try {
         return 10;
      } finally {
         return 20;
      }
   }

}
