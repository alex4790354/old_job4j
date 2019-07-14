package ru.job4j.tsk3_template_data;


import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

//@Ignore
public class UserStorageJdbcTest {
   @Test
   public void whenUserAdd() throws Exception {
      ApplicationContext context = new ClassPathXmlApplicationContext("jdbc-context.xml");
      UserStorageJdbc userStorageJdbc = context.getBean(UserStorageJdbc.class);
      User user = userStorageJdbc.save(new User("p123", "pass", "Petr", "petr@test.ru"));
      assertThat(userStorageJdbc.getAll().contains(user), is(true));
   }
}
