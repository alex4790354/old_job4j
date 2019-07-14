package ru.job4j.ch1_IoC2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.ch1_IoC2.model.User;

@Component
public class UserStorage {
   private final Storage storage;

   @Autowired
   public UserStorage(final Storage storage) {
      this.storage = storage;
   }

   public void add(User user) {
      this.storage.add(user);
   }

   public Storage getStorage() {
      return storage;
   }
}
