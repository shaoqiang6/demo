package com.shawn.jvm;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OOMObject {

    /**
     * -Xss128K
     * StackOverflowError
     */
    @Test
    public void testStackOOM() {
//        for (int i = 0; i < 100; i++) {
////            System.out.print("unused" +i +",");
////        }
        stackTest();
    }
    int stackLentch = 0;
    private void stackTest(){
        int unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99;
        stackLentch++;
        System.out.println(stackLentch);
        stackTest();

    }


    /**
     * jdk7 -XX:PermSize=10M -XX:MaxPermSize=10M
     * jdk8+  -XX:MaxMetaspaceSize=10M
     */
    @Test
    public void testPermOOM(){
        int i = 0;
        // java.lang.OutOfMemoryError: Metaspace
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setUseCache(false);
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, objects));
            enhancer.create();
            System.out.println(i++);
        }
    }
    private static int _1MB = 1024 * 1024;

    /**
     * -Xmx10M -XX:MaxDirectMemorySize=10M
     * java.lang.OutOfMemoryError
     * 	at sun.misc.Unsafe.allocateMemory(Native Method)
     * @throws Exception
     */
    @Test
    public void testDirectMemoryOOM() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }



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
