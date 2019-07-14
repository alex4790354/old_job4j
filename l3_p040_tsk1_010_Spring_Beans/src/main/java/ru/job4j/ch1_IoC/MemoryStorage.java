package ru.job4j.ch1_IoC;

import org.springframework.stereotype.Component;

@Component
public class MemoryStorage implements Storage {
   public void add(User user) {
      System.out.println("Recodr Stored in memory");
   }
}
