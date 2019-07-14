package ru.job4j.ch3_Sorting3;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListCompareTest {

   @Test
   public void whenStringsAreEqualThenZero () {
      ListCompare listCompare = new ListCompare();
      int rst = listCompare.compare(
            "Ivanov",
            "Ivanov"
      );
      assertThat(rst, is(0));
   }

   @Test
   public void whenLeftLessThanRightResultShouldBeNegative () {
      ListCompare listCompare = new ListCompare();
      int rst = listCompare.compare(
            "Ivanov",
            "Ivanova"
      );
      assertThat(rst, lessThan(0));
   }

   @Test
   public void whenLeftGreaterThanRightResultShouldBePositive () {
      ListCompare listCompare = new ListCompare();
      int rst = listCompare.compare(
            "Petrov",
            "Ivanova"
      );
      assertThat(rst, greaterThan(0));
   }

   @Test
   public void secondCharOfLeftGreaterThanRightShouldBePositive(){
      ListCompare listCompare = new ListCompare();
      int rst = listCompare.compare(
            "Petrov",
            "Patrov"
      );
      assertThat(rst, greaterThan(0));
   }

   @Test
   public void secondCharOfLeftLessThanRightShouldBeNegative(){
      ListCompare listCompare = new ListCompare();
      int rst = listCompare.compare(
            "Patrova",
            "Petrov"
      );
      assertThat(rst, lessThan(0));
   }

}
