package ru.job4j.ch01_Collections.task1;

import ru.job4j.ch01_Collections.task1.*;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {

   private List<Person> persons = new ArrayList<Person>();

   public void add(Person person) {
      this.persons.add(person);
   }

   /**
    * Вернуть список всех пользователей, который содержат key в любых полях.
    *
    * @param key Ключ поиска.
    * @return Список подощедщих пользователей.
    */
   public List<Person> find(String key) {
      List<Person> result = new ArrayList<Person>();
      // Можно так:
      /*for (int i = 0; i < persons.size(); i++) {
         if (this.persons.get(i).getName().contains(key) || this.persons.get(i).getSurname().contains(key) ||
             this.persons.get(i).getAddress().contains(key) || this.persons.get(i).getPhone().contains(key)) {
            result.add(persons.get(i));
         }
      }  //  Или так  */
      for (Person person : persons) {
         if (person.getName().contains(key) || person.getSurname().contains(key) ||
               person.getAddress().contains(key) || person.getPhone().contains(key)) {
            result.add(person);
         }
      }
         return result;
   }

}
