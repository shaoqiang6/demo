package com.shawn.asm.bytebuddy;

/**
 * @author yansq
 * @date 2020/12/16
 */
public class SingerMichael implements Singable {
    @Override
    public void sing(String songName) {
        System.out.println(" singing , name: " + songName);
    }
}
