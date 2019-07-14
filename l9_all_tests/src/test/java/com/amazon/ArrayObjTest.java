package com.amazon;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class ArrayObjTest {

    @Test
    void simpleOne() {
        int real = 1;
        int[][] testArr = {{-2, 1,  0, 1, 1},
                            {0, 1,  1, 1, 1},
                            {1, 1,  0, 1, 1},
                            {1, 0,  1, 1, 1},
                            {1, 1, -1, 1, 0}};
        ArrayObj arrayObj = new ArrayObj(testArr);
        arrayObj.writeNextWave();
        Assert.assertThat(arrayObj.simpleOne(), is(1));
    }
}
