package ru.job4j.tsk3_template_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
//@org.springframework.stereotype.Repository
public class UserStorageJdbc {
   private final JdbcTemplate template;

   @Autowired
   public UserStorageJdbc(final JdbcTemplate template) {
      this.template = template;
   }

   public User save(final User model) {
      final String INSERT_SQL = "insert into users(name) values(?)";
      KeyHolder keyHolder = new GeneratedKeyHolder();
      this.template.update(
            new PreparedStatementCreator() {
               public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                  PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[]{"id"});
                  ps.setString(1, model.getName());
                  return ps;
               }
             }, keyHolder);
      model.setId(keyHolder.getKey().intValue());
      return model;
   }

   public List<User> getAll() {
      return this.template.query("select * from users", new BeanPropertyRowMapper(User.class));
   }


}
