package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsDriveUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarsDriveUnitRepository extends JpaRepository<CarsDriveUnit, Integer> {

    public List<CarsDriveUnit> findAll();

}
