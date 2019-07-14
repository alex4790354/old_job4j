package ru.job4j.carsAdds.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.carsAdds.modles.CarAd;
import ru.job4j.carsAdds.repository.CarAdsRepository;
import ru.job4j.carsAdds.repository.CarAdsServiceInterf;

import java.util.List;


@Service
public class CarAdsService implements CarAdsServiceInterf {
    @Autowired
    private CarAdsRepository repository;

    @Override
    public List<CarAd> findAll() {
        List<CarAd> allCarsAdds = (List<CarAd>) repository.findAll();
        return allCarsAdds;
    }

}
