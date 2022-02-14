package com.shawn.thread;

import lombok.SneakyThrows;

/**
 * @author shaoqiangyan
 */
public class NotifyWait {
    private volatile int count;
    @SneakyThrows
    public synchronized void addCount() {
        System.out.println("waiting " + Thread.currentThread().getName());
        wait();
        count++;
        System.out.println(count + Thread.currentThread().getName());
    }



    public static void main(String[] args) throws InterruptedException {
        NotifyWait notifyWait1 = new NotifyWait();
        new Thread(() -> notifyWait1.addCount()).start();
        new Thread(() -> notifyWait1.addCount()).start();
        new Thread(() -> notifyWait1.addCount()).start();
        Thread.sleep(10L);
        synchronized (notifyWait1) {
//            notifyWait1.notify();
            notifyWait1.notifyAll();
        }
        System.out.println("...");

        Thread.sleep(10L);
    }
}
