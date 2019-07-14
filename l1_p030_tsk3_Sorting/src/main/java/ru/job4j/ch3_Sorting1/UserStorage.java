package ru.job4j.ch3_Sorting1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UserStorage { // implements Comparator<User> {
   List<User> allUsers = new ArrayList<User>();

   public void add(User user) {
      allUsers.add(user);
   }

   public List<User> getAllUsers() {
      return allUsers;
   }

   public List<User> sortList(List<User> list) {
      list.sort(
            new Comparator<User>() {
               int rslt;
               @Override
               public int compare(User o1, User o2) {
                  rslt = o1.getAge() - o2.getAge();
                  if (rslt == 0) {
                     rslt = o1.getName().compareTo(o2.getName());
                  }
                  return rslt;
               }
            }
      );
      return list;
   }

}
