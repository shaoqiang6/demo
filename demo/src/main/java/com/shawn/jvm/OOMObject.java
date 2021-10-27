package com.shawn.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OOMObject {

    /**
     * -Xms16M -Xmx16M -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void oom(){
        Map<Integer, List<Integer>> map = new HashMap<>(100);
        for (int v = 0; v < 100; v++) {
            for (int key = 10000000; key < 10000100; key++) {
                // java.lang.OutOfMemoryError: Java heap space
                // 这里的  ArrayList::new 等效于 k -> new ArrayList<>(k) ,每次都会初始化一个k大小的空数组
                map.computeIfAbsent(key, ArrayList::new).add(v);
//                map.computeIfAbsent(key, k -> new ArrayList<>(k)).add(v);
            }
        }
    }

}
