package ru.job4j.tsk3_example.app;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.job4j.tsk3_example.entities.ContactEntity;
import ru.job4j.tsk3_example.intf.ContactService;

import java.util.List;

// Это не сработало. Нужно искать другой пример.
public class Main {

   public static void main(String[] args) {

      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      ctx.load("classpath:spring-config.xml"); //move from src.main.java to src.main.resources
      ctx.refresh();

      ContactService service = ctx.getBean("jpaContactService", ContactService.class);
      List<ContactEntity> contacts = service.findAll();
      printAll(contacts);

      contacts = service.findByFirstName("Name1");
      printAll(contacts);

      contacts = service.findByFirstNameAndLastName("Name1", "LastName1");
      printAll(contacts);
   }

   private static void printAll(List<ContactEntity> contacts) {
      System.out.println("printAll: ");
      for (ContactEntity contact : contacts) {
         System.out.println(contact);
      }
   }
}
