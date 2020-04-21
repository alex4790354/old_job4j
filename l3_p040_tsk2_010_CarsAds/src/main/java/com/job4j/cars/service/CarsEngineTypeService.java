package com.job4j.cars.service;

import com.job4j.cars.entity.CarsEngineType;

import java.util.List;


public interface CarsEngineTypeService {

    public List<CarsEngineType> findAll();

    public CarsEngineType findById(int pId);

    public void save(CarsEngineType pCarsBodyType);

    public void deleteById(int pId);

}
