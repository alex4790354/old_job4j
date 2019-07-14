package ru.job4j.ch1_IoC2;

import org.springframework.stereotype.Component;
import ru.job4j.ch1_IoC2.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryStorage implements Storage {
   private List<User> userList = new ArrayList<User>();

   public void add(User user) {
      userList.add(user);

      System.out.println("Recodr Stored in memory");
   }

   public List<User> getUserList() {
      return userList;
   }

}
