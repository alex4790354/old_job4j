package ru.job4j.wiley2lvl;


import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.Assert.assertThat;


class TLCTimeTest {

   @Test
   void checkAddAndRemoveObjectLazy() throws IOException, ClassNotFoundException {
      int sAfter;
      int sBefore;
      TLC<MyObject> tlc = new TLC<MyObject>("Lazy", 12400000, 124000);
      sBefore = tlc.getDataSize();

      tlc.addObj(new MyObject(8, "8"));
      sAfter = tlc.getDataSize();
      assertThat(sAfter, Is.is(sBefore + 1));

      tlc.remObj(new MyObject(8, "8"));
      sAfter = tlc.getDataSize();
      assertThat(sAfter, Is.is(sBefore));
   }

   @Test
   void checkAddAndRemoveObjectTime() throws IOException, ClassNotFoundException {
      int sBefore;
      int sAfter;
      TLC<MyObject> tlc = new TLC<MyObject>("Time", 12400000, 124000);
      sBefore = tlc.getDataSize();

      tlc.addObj(new MyObject(8, "8"));
      sAfter = tlc.getDataSize();
      assertThat(sAfter, Is.is(sBefore + 1));

      tlc.remObj(new MyObject(8, "8"));
      sAfter = tlc.getDataSize();
      assertThat(sAfter, Is.is(sBefore));
   }

}