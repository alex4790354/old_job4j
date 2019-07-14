package ru.job4j.ch3_Sorting1;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class UserStorageTest {

   @Test
   public void whenAddNewUserToDB() {
      UserStorage userStorage = new UserStorage();
      userStorage.add(new User("Ivan", 60));
      userStorage.add(new User("Vasya", 30));
      userStorage.add(new User("Petya", 40));
      userStorage.add(new User("Alex", 30));
      List<User> allUser = userStorage.getAllUsers();
      Assert.assertThat(allUser.get(0).getName(), is("Ivan"));

      allUser = userStorage.sortList(allUser);
      Assert.assertThat(allUser.get(0).getName(), is("Alex"));

   }

}
