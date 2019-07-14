package ru.job4j.cars21.component;


//import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.job4j.cars21.models.CarBrand;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;


@Component
@Qualifier("CarBrands")
public class CarBrands implements Ads<CarBrand> {

   private JdbcTemplate jdbcTemplate;
   private String tableName;
   //private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AllCarsAds.class);

   public CarBrands(final JdbcTemplate jdbcTemplate) throws IOException {
      this.getProperties();
      this.jdbcTemplate = jdbcTemplate;
   }

   @Override
   public List<CarBrand> getAllObject() {
      final String query = String.format("select * from %s", this.tableName);
      return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CarBrand.class));
   }

   @Override
   public CarBrand getById(final int id) {
       List<CarBrand> allCarsBrands = jdbcTemplate.query(String.format("select * from %s where id =?", this.tableName),
           new Object[]{id}, (resultSet, i) -> new CarBrand(
                  resultSet.getInt("id"),
                  resultSet.getString("name")));
       if (allCarsBrands.size() == 1) {
          return allCarsBrands.get(0);
       } else {
          return null;
       }
   }

   @Override
   public CarBrand save(final CarBrand carBrand) {
       final String sql = String.format("insert into %s (name) values (?)", this.tableName);
       final KeyHolder keyHolder = new GeneratedKeyHolder();
       jdbcTemplate.update(
           connection -> {
               PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, carBrand.getName());
               return ps;
           }, keyHolder);
       carBrand.setId(keyHolder.getKey().intValue());
       return carBrand;
   }

   @Override
   public void deleteAds(final CarBrand carBrand) {
      jdbcTemplate.update(String.format("delete from %s where id = ?", this.tableName), carBrand.getId());
      //System.out.println("deleteObject in CarBrand");
   }

   private void getProperties() throws IOException {
      Properties properties = new Properties();
      try (InputStream in = this.getClass().getClassLoader().
              //getResourceAsStream("users")) {
                      getResourceAsStream("cars21/connection21.cars_brand.properties")) {
         properties.load(in);
         this.tableName = properties.getProperty("tableName");
      } catch (IOException e) {
         //LOG.error(e.getMessage(), e);
         e.printStackTrace();
      }
   }
}
