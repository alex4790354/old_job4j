package com.job4j.cars.service;

import com.job4j.cars.entity.CarsBrand;

import java.util.List;


public interface CarsBrandService {

    public List<CarsBrand> findAll();

    public CarsBrand findById(int pId);

    public void save(CarsBrand pEmployee);

    public void deleteById(int pId);

}
