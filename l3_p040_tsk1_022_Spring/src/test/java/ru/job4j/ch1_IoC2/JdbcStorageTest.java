package ru.job4j.ch1_IoC2;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.ch1_IoC2.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class JdbcStorageTest {

   @Test
   public void whenAddNewUserToDB() {
      int countBefore = ModuleDAO.INSTANCE().getIntFromSQL("SELECT count(*) FROM users");
      User user = new User(0, "testLogin", "testPass", "testName", "test@email.net");
      JdbcStorage jdbcStorage = new JdbcStorage();
      jdbcStorage.add(user);
      int countAfter = ModuleDAO.INSTANCE().getIntFromSQL("SELECT count(*) FROM users");

      System.out.println("Before: " + countBefore + ", after: " + countAfter);
      Assert.assertThat(countBefore + 1, is(countAfter));

      ModuleDAO.INSTANCE().execSQL("DELETE FROM users WHERE login = 'testLogin' ");
      countAfter = ModuleDAO.INSTANCE().getIntFromSQL("SELECT count(*) FROM users");
      Assert.assertThat(countBefore, is(countAfter));

   }
}
