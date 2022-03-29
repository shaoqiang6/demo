package com.shawn.adapter;

public interface Turn {
    void turnOn();
    default void turnOff() {
        System.out.println("off");
    }
}
