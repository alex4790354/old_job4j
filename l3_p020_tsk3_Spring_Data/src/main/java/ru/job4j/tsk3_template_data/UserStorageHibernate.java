package ru.job4j.tsk3_template_data;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


public class UserStorageHibernate {

   private final HibernateTemplate template;

   public UserStorageHibernate(final HibernateTemplate template) {
      this.template = template;
   }

   @Transactional
   public User save(User user) {
      this.template.save(user);
      return user;
   }

   public List<User> getAllUsers() {
      return (List<User>) this.template.find("from users");
   }

}
