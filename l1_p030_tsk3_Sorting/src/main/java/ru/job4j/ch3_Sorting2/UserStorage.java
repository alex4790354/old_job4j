package ru.job4j.ch3_Sorting2;

import ru.job4j.ch3_Sorting1.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserStorage {
   List<User> allUsers = new ArrayList<User>();

   public void add(User user) {
      allUsers.add(user);
   }

   public List<User> getAllUsers() {
      return allUsers;
   }

   public List<User> sortByNameLength(List<User> list) {
      list.sort(
            new Comparator<User>() {
               @Override
               public int compare(User user1, User user2) {
                  return user1.getName().length() - user2.getName().length();
               }
            }
      );
      return list;
   }

   public List<User> sortByAllFields(List<User> list) {
      list.sort(
            new Comparator<User>() {
               @Override
               public int compare(User user1, User user2) {
                  int result;
                  result = user1.getName().compareTo(user1.getName());
                  if (result == 0) {
                     result = user1.getAge() - user2.getAge();
                  }
                  return result;
               }
            }
      );
      return list;
   }

}
