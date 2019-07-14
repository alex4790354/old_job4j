package ru.job4j.cars21.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.job4j.cars21.component.Ads;
import ru.job4j.cars21.models.CarAd;
import java.util.List;


@Component
public class CarAdsStorage implements Ads<CarAd> {

   private final Ads ads;

   @Autowired
   public CarAdsStorage(@Qualifier("CarAds") final Ads ads) {
      this.ads = ads;
   }

   @Override
   public List<CarAd> getAllObject() {
      return ads.getAllObject();
   }

   @Override
   public CarAd getById(final int adId) {
      return (CarAd) this.ads.getById(adId);
   }

   @Override
   public CarAd save(final CarAd carAd) {
      return (CarAd) this.ads.save(carAd);
   }

   @Override
   public void deleteAds(CarAd carAd) {
      this.ads.deleteAds(carAd);
   }

}
