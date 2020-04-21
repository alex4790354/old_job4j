package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface CarsAdsRepository extends JpaRepository<CarsAd, Integer> {

    // no need to write any code!

    // add a method to sort by last name
    @Query("select c from CarsAd c ORDER BY c.carsBrand.name, c.carsModel.name")
    public List<CarsAd> findAll();

}
