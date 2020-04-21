package com.job4j.cars.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars_ad")
public class CarsAd {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int  id;

    @Column(name="cars_brand_id")
    private int  carsBrandId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_brand_id", insertable = false, updatable = false)
    private CarsBrand carsBrand;

    @Column(name="cars_model_id")
    private int  carsModelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_model_id", insertable = false, updatable = false)
    private CarsModel carsModel;

    @Column(name="cars_body_type_id")
    private int  carsBodyTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_body_type_id", insertable = false, updatable = false)
    private CarsBodyType carsBodyType;

    @Column(name="cars_transmission_id")
    private int  carsTransmissionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_transmission_id", insertable = false, updatable = false)
    private CarsTransmission carsTransmission;

    @Column(name="cars_engine_type_id")
    private int  carsEngineTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_engine_type_id", insertable = false, updatable = false)
    private CarsEngineType carsEngineType;

    @Column(name="cars_drive_unit_id")
    private int  carsDriveUnitId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_drive_unit_id", insertable = false, updatable = false)
    private CarsDriveUnit carsDriveUnit;

    @Column(name="mileage")
    private int mileage;

    @Column(name="description")
    private String description;

    @Column(name="user_id")
    private int userId;

    @Column(name="photo_id")
    private int photoId;

    @OneToMany(mappedBy = "carsAd", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CarsPhoto> carsPhotos;

    @Column(name="status")
    private String status;


    // Normal constractor, getters and setters:
    public CarsAd() {
    }

    public CarsAd(int id,
                  int carsBrandId,
                  int carsModelId,
                  int carsBodyTypeId,
                  int carsTransmissionId,
                  int carsEngineTypeId,
                  int carsDriveUnitId,
                  int  mileage,
                  String description,
                  int userId,
                  int photoId,
                  String status,
                  Set<CarsPhoto> carsPhotos) {
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
        this.carsPhotos = carsPhotos;
    }

    public CarsAd(int carsBrandId,
                  int carsModelId,
                  int carsBodyTypeId,
                  int carsTransmissionId,
                  int carsEngineTypeId,
                  int carsDriveUnitId,
                  int  mileage,
                  String description,
                  int userId, int photoId,
                  String status,
                  Set<CarsPhoto> carsPhotos) {
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
        this.carsPhotos = carsPhotos;
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

    public String getCarsBrandIdString() {
        return String.valueOf(carsBrandId);
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

    public void setCarsBrand(CarsBrand carsBrand) {
        this.carsBrand = carsBrand;
    }

    public CarsBrand getCarsBrand() {
        return carsBrand;
    }

    public void setCarsModel(CarsModel carsModel) {
        this.carsModel = carsModel;
    }

    public CarsModel getCarsModel() {
        return carsModel;
    }

    public int getCarsBodyTypeId() {
        return carsBodyTypeId;
    }

    public void setCarsBodyTypeId(int carsBodyTypeId) {
        this.carsBodyTypeId = carsBodyTypeId;
    }

    public void setCarsBodyType(CarsBodyType carsBodyType) {
        this.carsBodyType = carsBodyType;
    }

    public CarsBodyType getCarsBodyType() {
        return carsBodyType;
    }

    public int getCarsTransmissionId() {
        return carsTransmissionId;
    }

    public void setCarsTransmissionId(int carsTransmissionId) {
        this.carsTransmissionId = carsTransmissionId;
    }

    public void setCarsTransmission(CarsTransmission carsTransmission) {
        this.carsTransmission = carsTransmission;
    }

    public CarsTransmission getCarsTransmission() {
        return carsTransmission;
    }

    public int getCarsEngineTypeId() {
        return carsEngineTypeId;
    }

    public void setCarsEngineTypeId(int carsEngineTypeId) {
        this.carsEngineTypeId = carsEngineTypeId;
    }

    public void setCarsEngineType(CarsEngineType carsEngineType) {
        this.carsEngineType = carsEngineType;
    }

    public CarsEngineType getCarsEngineType() {
        return carsEngineType;
    }

    public int getCarsDriveUnitId() {
        return carsDriveUnitId;
    }

    public void setCarsDriveUnitId(int carsDriveUnitId) {
        this.carsDriveUnitId = carsDriveUnitId;
    }

    public void setCarsDriveUnit(CarsDriveUnit carsDriveUnit) {
        this.carsDriveUnit = carsDriveUnit;
    }

    public CarsDriveUnit getCarsDriveUnit() {
        return carsDriveUnit;
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

    public Set<CarsPhoto> getCarsPhotos() {
        return carsPhotos;
    }

    public void setCarsPhotos(Set<CarsPhoto> carsPhotos) {
        this.carsPhotos = carsPhotos;
    }
}
