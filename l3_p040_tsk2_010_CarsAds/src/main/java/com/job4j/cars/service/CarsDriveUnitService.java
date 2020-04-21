package com.job4j.cars.service;

import com.job4j.cars.entity.CarsDriveUnit;

import java.util.List;


public interface CarsDriveUnitService {

    public List<CarsDriveUnit> findAll();

    public CarsDriveUnit findById(int pId);

    public void save(CarsDriveUnit pCarsBodyType);

    public void deleteById(int pId);

}
