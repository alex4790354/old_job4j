package com.job4j.cars.entity;

import javax.persistence.*;

@Entity
@Table(name="cars_photo")
public class CarsPhoto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int ID;

    @Column(name="ad_id")
    private int adId;

    //{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="ad_id", insertable = false, updatable = false)
    private CarsAd carsAd;

    public CarsPhoto() {
    }

    public CarsPhoto(int adId) {
        this.adId = adId;
    }

    public CarsPhoto(int adId, CarsAd carsAd) {
        this.adId = adId;
        this.carsAd = carsAd;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public CarsAd getCarsAd() {
        return carsAd;
    }

    public void setCarsAd(CarsAd carsAd) {
        this.carsAd = carsAd;
    }



}
