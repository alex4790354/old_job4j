package ru.job4j.ch3_Sorting2;


import org.junit.Assert;
import org.junit.Test;
import ru.job4j.ch3_Sorting1.User;
import ru.job4j.ch3_Sorting2.UserStorage;
import java.util.List;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

   @Test
   public void sortByAllFieldsTest() {
      ru.job4j.ch3_Sorting2.UserStorage userStorage = new UserStorage();
      userStorage.add(new User("Ivan", 60));
      userStorage.add(new User("Alexandr", 30));
      userStorage.add(new User("Petya", 40));
      userStorage.add(new User("Alexandr", 20));
      List<User> allUser = userStorage.getAllUsers();
      Assert.assertThat(allUser.get(0).getName(), is("Ivan"));

      allUser = userStorage.sortByAllFields(allUser);
      Assert.assertThat(allUser.get(0).getName(), is("Alexandr"));
      Assert.assertThat(allUser.get(0).getAge(), is(20));
   }

   @Test
   public void sortByNameLengthTest() {
      ru.job4j.ch3_Sorting2.UserStorage userStorage = new UserStorage();
      userStorage.add(new User("Alexandr", 65));
      userStorage.add(new User("Petya", 35));
      userStorage.add(new User("Alexandr", 45));
      userStorage.add(new User("Ivan", 25));
      List<User> allUser = userStorage.getAllUsers();
      Assert.assertThat(allUser.get(0).getName(), is("Alexandr"));

      allUser = userStorage.sortByNameLength(allUser);
      Assert.assertThat(allUser.get(0).getName(), is("Ivan"));

   }


}
