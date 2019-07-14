package ru.job4j.wiley;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Cache.
 *
 * @author Alexander Vasiliev.
 * @version 1.00.
 * @since 19.03.2018.
 */
public class Cache {
   private ConcurrentHashMap<String, MyUser> map = new ConcurrentHashMap<String, MyUser>();
   private int maxCashSize = 1024;
   private int maxFileSize = 1024 * 1024;
   private String strategy; // Tm - удаление кеша по времени, Mx - в случае достижения максимум, Lz - только первые
   // На первом этапе

   public static void main(String[] args) {
      int a = (int) Math.sqrt(14 % 10);
   System.out.println(a);








   }
}