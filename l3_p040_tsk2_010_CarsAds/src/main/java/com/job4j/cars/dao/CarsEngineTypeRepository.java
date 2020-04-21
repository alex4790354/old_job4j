package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsEngineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarsEngineTypeRepository extends JpaRepository<CarsEngineType, Integer> {

    public List<CarsEngineType> findAll();

}
