package com.job4j.cars.service;

import com.job4j.cars.entity.CarsModel;

import java.util.List;


public interface CarsModelService {

    public List<CarsModel> findAll();
    //public List<CarsModel> findAll(int pBrandId);

    public List<CarsModel> findByBrandId(int pBrandId);
    //public List<CarsModel> findAll(int pBrandId);

    public CarsModel findById(int theId);

    public void save(CarsModel theEmployee);

    public void deleteById(int theId);

}
