package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CarsModelRepository extends JpaRepository<CarsModel, Integer> {

    // add a method to sort by last name
    public List<CarsModel> findAll();

    //@Query("select c from CarsModel c where c.brandId = ?1")
    List<CarsModel> findByBrandId(int brandId);

}
