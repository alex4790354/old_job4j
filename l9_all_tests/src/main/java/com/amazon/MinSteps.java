package com.amazon;

public class MinSteps {

    public int[][] writeNextStep(int[][] inpArr) {
        int[][] outArr = inpArr;
        outArr[3][3] = 5;
        return outArr;
    }

    public static void main(String[] args) {
        int curentStep = 1;
        int[][] testArr = {{-2, 1,  1, 1, 1},
                            {0, 1,  0, 1, 1},
                            {1, 1,  0, 1, 1},
                            {1, 0,  1, 1, 1},
                            {1, 1, -1, 1, 0}};
        ArrayObj arrayObj = new ArrayObj(testArr);
        arrayObj.printArray();
        for (int i = 0; i < testArr.length * testArr[0].length; i++) {
            arrayObj.writeNextWave();
            if (arrayObj.getMinSteps() > 0) {
                System.out.println("Find it. Min path = " + arrayObj.getMinSteps());
                break;
            }
        }

    }
}
