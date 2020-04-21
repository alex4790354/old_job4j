package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsBodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CarsBodyTypeRepository extends JpaRepository<CarsBodyType, Integer> {

    public List<CarsBodyType> findAll();

}
