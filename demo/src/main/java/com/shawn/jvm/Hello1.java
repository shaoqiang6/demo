package com.shawn.jvm;

/**
 * @author yansq
 * @date 2020/10/15
 */
public class Hello1 {

    public void test(){
        int a = 1;
        byte b = 1;
        int c = (a + b) * 3;
    }

    public void ifTest(int x) {
        MovingAverage movingAverage = new MovingAverage();
        double[] nums = new double[]{1,3,4};
        for (double num : nums) {
            movingAverage.submit(num);
        }
        double v = movingAverage.getAvg();

    }
}

