package com.job4j.cars.service;

import com.job4j.cars.entity.CarsBodyType;
import java.util.List;


public interface CarsBodyTypeService {

    public List<CarsBodyType> findAll();

    public CarsBodyType findById(int pId);

    public void save(CarsBodyType pCarsBodyType);

    public void deleteById(int pId);

}
