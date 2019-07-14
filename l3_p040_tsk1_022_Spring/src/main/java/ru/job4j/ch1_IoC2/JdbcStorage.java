package ru.job4j.ch1_IoC2;

import ru.job4j.ch1_IoC2.model.User;

public class JdbcStorage implements Storage {
   public void add(User user) {
      String sql = String.format("INSERT INTO users (login, password, name, inserted_date, email) VALUES('%s', '%s', '%s', CURRENT_TIMESTAMP(0), '%s') ",
            user.getLogin(), user.getPassword(), user.getName(), user.getEmail());
      ModuleDAO.INSTANCE().execSQL(sql);
      System.out.println("Recodr Stored in DB over JDBC");
   }
}
