package com.amazon;

public class ArrayObj {
    private int[][] testArr;
    private int arrWith;
    private int arrHeight;
    private int currStep;
    private int minSteps;

    public ArrayObj(int[][] testArr) {
        this.testArr = testArr;
        this.arrHeight = testArr.length;
        this.arrWith = testArr[0].length;
        this.currStep = 1;
        this.minSteps = 0;
    }

    public int simpleOne() {
        return 1;
    }

    public void writeNextWave() {
        if (this.currStep == 1) {
            // check only dots: 1:0 and 0:1
            if (this.testArr[1][0] == 1) {
                this.testArr[1][0] = 2;
            }
            if (this.testArr[0][1] == 1) {
                this.testArr[0][1] = 2;
            }
        } else {
            for (int hight = 0; hight < this.arrHeight; hight++) {
                for (int with = 0; with < this.arrWith; with++) {
                    if (this.testArr[hight][with] == this.currStep) {
                        // now real work: write steps amount in our Array

                        // write cell up:
                        if (hight > 0) {
                            if (this.testArr[hight - 1][with] == 1) {
                                this.testArr[hight - 1][with] = this.currStep + 1;
                            } else if (this.testArr[hight - 1][with] == -1) {
                                if (this.minSteps == 0) {
                                    this.minSteps = currStep;
                                }
                            }
                        }
                        // write cell down:
                        if (hight < this.arrHeight - 1) {
                            if (this.testArr[hight + 1][with] == 1) {
                                this.testArr[hight + 1][with] = this.currStep + 1;
                            } else if (this.testArr[hight + 1][with] == -1) {
                                if (this.minSteps == 0) {
                                    this.minSteps = currStep;
                                }
                            }
                        }
                        // write cell right:
                        if (with < this.arrWith - 1) {
                            if (this.testArr[hight][with + 1] == 1) {
                                this.testArr[hight][with + 1] = this.currStep + 1;
                            } else if (this.testArr[hight][with + 1] == -1) {
                                if (this.minSteps == 0) {
                                    this.minSteps = currStep;
                                }
                            }
                        }
                        // write cell left:
                        if (with > 0) {
                            if (this.testArr[hight][with - 1] == 1) {
                                this.testArr[hight][with - 1] = this.currStep + 1;
                            } else if (this.testArr[hight][with - 1] == -1) {
                                if (this.minSteps == 0) {
                                    this.minSteps = currStep;
                                }
                            }
                        }
                    }
                }
            }
        }

        this.currStep++;
        System.out.println(this.currStep);
        printArray();
    }

    public void printArray() {
        for (int iHeight = 0; iHeight < this.arrHeight; iHeight++) {
            for (int iWith = 0; iWith < this.arrWith; iWith++) {
                System.out.print(testArr[iHeight][iWith]);
            }
            System.out.println("");
        }
    }

    public int[][] getTestArr() {
        return testArr;
    }
    public int getMinSteps() {
        return minSteps;
    }
}
