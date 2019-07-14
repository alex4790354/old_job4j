package ru.job4j.mindgeek;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class NextTest {

   private String name;

   private String hello;

   public NextTest(String name) { this.name = name; }

   public String getHello() { return hello; }

   public static void main(String[] args) {


      System.out.println(new NextTest("name").getHello() instanceof String);

   }


}





   /*@Override
   public void run() {
      System.out.println(String.format("%s TODO asynch", this.name));
   }

   public static void main(String[] args) {
      System.out.println("Start");
      new Thread(new NextTest("Tread 1")).start();
      new Thread(new NextTest("Tread 2")).start();
      System.out.println("Finish");
   }*/



