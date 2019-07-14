package ru.job4j.ch1_IoC;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;


public class MemoryStorageTest {

   @Test
   public void whenAddUserToStorageShouldSaveIt() {
      MemoryStorage memoryStorage = new MemoryStorage();
      UserStorage userStorage = new UserStorage(memoryStorage);
      userStorage.add(new User());
      assertNotNull(userStorage);
   }

   @Test
   public void whenLoadContextShouldGetBean() {
      ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
      MemoryStorage memoryStorage = context.getBean(MemoryStorage.class);
      memoryStorage.add(new User());
      assertNotNull(memoryStorage);
   }

   @Test
   public void whenLoadContextShouldGetBean2() {
      ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
      UserStorage userStorage = context.getBean(UserStorage.class);
      userStorage.add(new User());
      assertNotNull(userStorage);
   }


}
