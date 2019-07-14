package ru.job4j.cars21.component;


//import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.job4j.cars21.models.CarAd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;


@Component
@Qualifier("CarAds")
public class CarAds implements Ads<CarAd> {

   private JdbcTemplate jdbcTemplate;
   private String tableName;
   //private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AllCarsAds.class);

   public CarAds(final JdbcTemplate jdbcTemplate) throws IOException {
      this.getProperties();
      this.jdbcTemplate = jdbcTemplate;
   }

   /*@Autowired
   public AllCarsAds(final Ads ads, String NextTest) {
      this.ads = ads;
      System.out.println("UserStorage00 awoke with param");
   }*/

   @Override
   public List<CarAd> getAllObject() {
      final String query = String.format("select * from %s", this.tableName);
      return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CarAd.class));
   }

   @Override
   public CarAd getById(final int id) {
       List<CarAd> allCarsAds = jdbcTemplate.query(String.format("select * from %s where id =?", this.tableName),
           new Object[]{id}, (resultSet, i) -> new CarAd(
                  resultSet.getInt("id"),
                  resultSet.getInt("cars_brand_id"),
                  resultSet.getInt("cars_model_id"),
                  resultSet.getInt("cars_body_type_id"),
                  resultSet.getInt("cars_transmission_id"),
                  resultSet.getInt("cars_engine_type_id"),
                  resultSet.getInt("cars_drive_unit_id"),
                  resultSet.getInt("mileage"),
                  resultSet.getString("description"),
                  resultSet.getInt("user_id"),
                  resultSet.getInt("photo_id"),
                  resultSet.getString("status"),
                  resultSet.getTimestamp("inserted_date")));
       if (allCarsAds.size() == 1) {
          return allCarsAds.get(0);
       } else {
          return null;
       }
   }

   @Override
   public CarAd save(final CarAd carAd) {
       final String sql = String.format("insert into %s (cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, "
              + " cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, status, inserted_date) "
              + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", this.tableName);
       final KeyHolder keyHolder = new GeneratedKeyHolder();
       jdbcTemplate.update(
           connection -> {
               PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
               ps.setInt(1, carAd.getCarsBrandId());
               ps.setInt(2, carAd.getCarsModelId());
               ps.setInt(3, carAd.getCarsBodyTypeId());
               ps.setInt(4, carAd.getCarsTransmissionId());
               ps.setInt(5, carAd.getCarsEngineTypeId());
               ps.setInt(6, carAd.getCarsDriveUnitId());
               ps.setInt(7, carAd.getMileage());
            ps.setString(8, carAd.getDescription());
               ps.setInt(9, carAd.getUserId());
              ps.setInt(10, carAd.getPhotoId());
           ps.setString(11, carAd.getStatus());
        ps.setTimestamp(12, carAd.getInsertedDate());
               return ps;
           }, keyHolder);
       carAd.setId(keyHolder.getKey().intValue());
       return carAd;
   }

   @Override
   public void deleteAds(final CarAd carAd) {
      jdbcTemplate.update(String.format("delete from %s where id = ?", this.tableName), carAd.getId());
   }

   private void getProperties() throws IOException {
      Properties properties = new Properties();
      try (InputStream in = this.getClass().getClassLoader().
              //getResourceAsStream("users")) {
                      getResourceAsStream("cars21/connection21.cars_ad.properties")) {
         properties.load(in);
         this.tableName = properties.getProperty("tableName");
      } catch (IOException e) {
         //LOG.error(e.getMessage(), e);
         e.printStackTrace();
      }
   }
}
