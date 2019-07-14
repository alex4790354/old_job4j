package ru.job4j.cars21.component;

import java.util.List;

public interface Ads<T> {

   List<T> getAllObject();
   T getById(final int id);
   T save(final T entity);
   void deleteAds(final T entity);

}
