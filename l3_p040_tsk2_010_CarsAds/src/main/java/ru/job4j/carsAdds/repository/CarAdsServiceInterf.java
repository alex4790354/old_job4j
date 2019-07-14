package ru.job4j.carsAdds.repository;


import ru.job4j.carsAdds.modles.CarAd;
import java.util.List;

public interface CarAdsServiceInterf {
    public List<CarAd> findAll();
}
