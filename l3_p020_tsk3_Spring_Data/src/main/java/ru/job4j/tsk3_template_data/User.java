package ru.job4j.tsk3_template_data;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   private String login;
   private String password;
   private String email;

   public User( String login, String password, String name, String email) {
      //this.id = id;
      this.login = login;
      this.password = password;
      this.name = name;
      this.email = email;
   }

   public int getId() {
      return id;
   }

   public String getLogin() {
      return login;
   }

   public String getPassword() {
      return password;
   }

   public String getName() {
      return name;
   }

   public String getEmail() {
      return email;
   }

   public void setId(int id) {
      this.id = id;
   }

}
