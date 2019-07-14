package ru.job4j.carsAdds.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.carsAdds.modles.CarAd;

@Repository
public interface CarAdsRepository extends CrudRepository<CarAd, Long> {
}
