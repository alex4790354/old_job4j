package ru.job4j.cars21.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import ru.job4j.cars21.component.Ads;
import ru.job4j.cars21.models.CarBrand;

@Component
public class CarBrandsStorage implements Ads<CarBrand> {

   private final Ads ads;

   @Autowired
   public CarBrandsStorage(@Qualifier("CarBrands") final Ads ads) {
      this.ads = ads;
   }

   @Override
   public List<CarBrand> getAllObject() {
      return ads.getAllObject();
   }

   @Override
   public CarBrand getById(final int adId) {
      return (CarBrand) this.ads.getById(adId);
   }

   @Override
   public CarBrand save(final CarBrand carBrand) {
      return (CarBrand) this.ads.save(carBrand);
   }

   @Override
   public void deleteAds(CarBrand carBrand) {
      this.ads.deleteAds(carBrand);
   }

}
