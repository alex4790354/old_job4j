package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsTransmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarsTransmissionRepository extends JpaRepository<CarsTransmission, Integer> {

    public List<CarsTransmission> findAll();

}
