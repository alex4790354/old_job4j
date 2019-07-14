package ru.job4j.cars21.models;

import org.springframework.stereotype.Component;
import java.sql.Timestamp;


public class CarAd {

   private int id;
   private int carsBrandId;
   private int carsModelId;
   private int carsBodyTypeId;
   private int carsTransmissionId;
   private int carsEngineTypeId;
   private int carsDriveUnitId;
   private int mileage;
   private String description;
   private int userId;
   private int photoId;
   private String status;                 //-- Y - for sale, N - NOT for sale. Can be M - moderator, H - hold, VIP etc.
   private Timestamp insertedDate;

   public CarAd() {
   }

   public CarAd(int id, int carsBrandId, int carsModelId, int carsBodyTypeId, int carsTransmissionId, int carsEngineTypeId,
       int carsDriveUnitId, int mileage, String description, int userId, int photoId, String status, Timestamp insertedDate) {
      this.id = id;
      this.carsBrandId = carsBrandId;
      this.carsModelId = carsModelId;
      this.carsBodyTypeId = carsBodyTypeId;
      this.carsTransmissionId = carsTransmissionId;
      this.carsEngineTypeId = carsEngineTypeId;
      this.carsDriveUnitId = carsDriveUnitId;
      this.mileage = mileage;
      this.description = description;
      this.userId = userId;
      this.photoId = photoId;
      this.status = status;
      this.insertedDate = insertedDate;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getCarsBrandId() {
      return carsBrandId;
   }

   public void setCarsBrandId(int carsBrandId) {
      this.carsBrandId = carsBrandId;
   }

   public int getCarsModelId() {
      return carsModelId;
   }

   public void setCarsModelId(int carsModelId) {
      this.carsModelId = carsModelId;
   }

   public int getCarsBodyTypeId() {
      return carsBodyTypeId;
   }

   public void setCarsBodyTypeId(int carsBodyTypeId) {
      this.carsBodyTypeId = carsBodyTypeId;
   }

   public int getCarsTransmissionId() {
      return carsTransmissionId;
   }

   public void setCarsTransmissionId(int carsTransmissionId) {
      this.carsTransmissionId = carsTransmissionId;
   }

   public int getCarsEngineTypeId() {
      return carsEngineTypeId;
   }

   public void setCarsEngineTypeId(int carsEngineTypeId) {
      this.carsEngineTypeId = carsEngineTypeId;
   }

   public int getCarsDriveUnitId() {
      return carsDriveUnitId;
   }

   public void setCarsDriveUnitId(int carsDriveUnitId) {
      this.carsDriveUnitId = carsDriveUnitId;
   }

   public int getMileage() {
      return mileage;
   }

   public void setMileage(int mileage) {
      this.mileage = mileage;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public int getPhotoId() {
      return photoId;
   }

   public void setPhotoId(int photoId) {
      this.photoId = photoId;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Timestamp getInsertedDate() {
      return insertedDate;
   }

   public void setInsertedDate(Timestamp insertedDate) {
      this.insertedDate = insertedDate;
   }
}



