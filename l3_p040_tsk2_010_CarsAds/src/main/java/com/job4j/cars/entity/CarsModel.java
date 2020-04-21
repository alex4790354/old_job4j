package com.job4j.cars.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars_model")
public class CarsModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int  id;

    @Column(name="brand_id")
    private int brandId;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "carsModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CarsAd> carsAds;

    // Normal constractor, getters and setters:
    public CarsModel(int pId, int pBrandId, String pName) {
        this.id = pId;
        this.brandId = pBrandId;
        this.name = pName;
    }

    public CarsModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CarsAd> getCarsAds() {
        return carsAds;
    }

    public void setCarsAds(Set<CarsAd> carsAds) {
        this.carsAds = carsAds;
    }

}
