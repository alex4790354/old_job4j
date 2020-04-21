package com.job4j.cars.service;

import com.job4j.cars.entity.CarsTransmission;

import java.util.List;


public interface CarsTransmissionService {

    public List<CarsTransmission> findAll();

    public CarsTransmission findById(int pId);

    public void save(CarsTransmission pCarsBodyType);

    public void deleteById(int pId);

}
