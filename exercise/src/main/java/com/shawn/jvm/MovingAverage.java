package com.shawn.jvm;

/**
 * @author yansq
 * @date 2020/10/15
 */
public class MovingAverage {
    private int  count = 0;
    private double sum = 0.0d;
    public void submit(double value){
        count ++;
        sum += value;
    }
    public double getAvg(){
        if(0 == this.count){
            return sum;
        }
        return sum/count;
    }
}
