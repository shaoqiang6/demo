package com.shawn.thread;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yansq
 * @date 2020/10/31
 */
public class Counter {

    private int sum;
    private volatile int voSum;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void incr3(){
        // 不是原子操作
        voSum ++;
    }
    public int getVoSum() {
        return voSum;
    }


    public void incr2() {
        atomicInteger.incrementAndGet();
    }
    public int getAtomicInteger() {
        return atomicInteger.intValue();
    }

    public void incr() {
        sum++;
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws IOException {

        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incr();
                counter.incr2();
                counter.incr3();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incr();
                counter.incr2();
                counter.incr3();
            }
        });
        thread2.start();
        System.in.read();
        System.out.println(counter.getSum());
        System.out.println(counter.getAtomicInteger());
        System.out.println(counter.getVoSum());

    }
}
