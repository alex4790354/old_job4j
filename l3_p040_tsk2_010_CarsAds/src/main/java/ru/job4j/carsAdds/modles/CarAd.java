package ru.job4j.carsAdds.modles;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CARS_ADS")
public class CarAd {

   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private int id;
   // cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, , user_id, photo_id, status, inserted_date
   private int cars_brand_id;
   private int cars_model_id;
   private String description;

   //private String status;                 //-- Y - for sale, N - NOT for sale. Can be M - moderator, H - hold, VIP etc.
   //private Timestamp insertedDate;

   public CarAd() {
   }

   public CarAd(int cars_brand_id, int cars_model_id, String description) {
      this.cars_brand_id = cars_brand_id;
      this.cars_model_id = cars_model_id;
      this.description = description;
   }

   public CarAd(int id, int cars_brand_id, int cars_model_id, String description) {
      this.id = id;
      this.cars_brand_id = cars_brand_id;
      this.cars_model_id = cars_model_id;
      this.description = description;
   }

   @Override
   public int hashCode() {
      return this.id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getCars_brand_id() {
      return cars_brand_id;
   }

   public void setCars_brand_id(int cars_brand_id) {
      this.cars_brand_id = cars_brand_id;
   }

   public int getCars_model_id() {
      return cars_model_id;
   }

   public void setCars_model_id(int cars_model_id) {
      this.cars_model_id = cars_model_id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}



