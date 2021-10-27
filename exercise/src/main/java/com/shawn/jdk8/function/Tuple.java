package com.shawn.jdk8.function;

/**
 * @author yanshaoqiang
 * @date 2018/12/22 14:35
 */
public class Tuple<T, U> {
    public final T _1;
    public final U _2;

    public Tuple(T t, U u){
        this._1 = t;
        this._2 = u;
    }

}
