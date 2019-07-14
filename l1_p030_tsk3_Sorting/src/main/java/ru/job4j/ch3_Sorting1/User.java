package ru.job4j.ch3_Sorting1;


public class User implements Comparable<User> {
   private String name;
   private int age;

   public User (String name, int age) {
      this.name = name;
      this.age = age;
   }

   /*public int compareTo(Object o) {
      return 0;
   }*/

   public String getName() {
      return name;
   }

   public int getAge() {
      return age;
   }

   @Override
   public String toString() {
      return String.format("%s лет, %s", age, name);
   }

   public int compareTo(User o2) {
      int result;
      if (o2.equals(null)) {
         result = 0;
      } else {
         if (age > o2.getAge()) {
            result = 1;
         } else if (age < o2.getAge()) {
            result = -1;
         } else {
            result = name.compareTo(o2.getName());
         }
      }
      return result;
   }
}
