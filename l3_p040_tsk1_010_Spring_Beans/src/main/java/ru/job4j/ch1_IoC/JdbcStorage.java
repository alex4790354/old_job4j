package ru.job4j.ch1_IoC;

public class JdbcStorage implements Storage {
   public void add(User user) {
      System.out.println("Recodr Stored in DB over JDBC");
   }
}
