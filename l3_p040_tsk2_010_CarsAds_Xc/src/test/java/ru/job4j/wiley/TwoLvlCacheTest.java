package ru.job4j.wiley;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TwoLvlCacheTest {

   @Test
   void checkAmountAfterAddNewOne() throws IOException {
      TwoLvlCache myCache = new TwoLvlCache(64000);
      int hsBefore = myCache.getDataSize();
      myCache.addObj(new MyUser(1, 1));
      int hsAfter = myCache.getDataSize();
      assertThat(hsAfter, Is.is(hsBefore + 1));
   }

}