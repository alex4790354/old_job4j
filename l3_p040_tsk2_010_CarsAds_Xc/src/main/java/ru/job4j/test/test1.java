package ru.job4j.test;

public class test1 {
    static int i = 5;

    public static void test() {
       System.out.println("Hello from test1");
       Object obj = new Object();
   }

   public static void main(String[] ags) {
       i = 6;
       System.out.println(test1.i);

   }

}
