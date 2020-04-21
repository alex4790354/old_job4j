package com.job4j.cars.service;

import com.job4j.cars.entity.CarsAd;
import java.util.List;


public interface CarsAdsService {

    public List<CarsAd> findAll();

    public CarsAd findById(int theId);

    public void save(CarsAd theEmployee);

    public void deleteById(int theId);

}
