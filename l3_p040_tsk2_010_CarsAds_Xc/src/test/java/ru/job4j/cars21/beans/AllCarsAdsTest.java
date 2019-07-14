package ru.job4j.cars21.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.cars21.models.CarAd;
import ru.job4j.cars21.component.Ads;

public class AllCarsAdsTest {

   @Test
   public void deleteInAllCarsAds() throws Exception {
      ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
      Ads<CarAd> ads = context.getBean(CarAdsStorage.class);

      CarAd carAdTwo = ads.getById(19);
      carAdTwo.setId(20);

      //ads.save(carAdTwo);
      System.out.println("Yo");
   }

}