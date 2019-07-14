package ru.job4j.wiley2lvl;


import java.io.IOException;
import java.lang.ref.SoftReference;


/**
 * @author   A_Vasiliev  9720560@gmail.com
 * @since    25.03.2018
 * @version  1.0.0
 */

public interface Cashe<T> {

   SoftReference<T> get(int casheID) throws IOException, ClassNotFoundException;

   boolean addObj(T obj) throws IOException;

   boolean remObj(T remObj) throws IOException, ClassNotFoundException;

   int getDataSize();

}




