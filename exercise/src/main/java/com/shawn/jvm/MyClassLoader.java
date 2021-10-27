package com.shawn.jvm;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

/**
 * @author yansq
 * @date 2020/10/15
 */
public class MyClassLoader extends ClassLoader {
    private String path;
    public MyClassLoader(String path){
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 绝对路径
        String filePath = path + File.separator + name;
        try {
            // 抽象路径下文件
            File file = new File(filePath);
            // 读取class文件到内存
            byte[] allBytes = Files.readAllBytes(Paths.get(file.toURI()));
            // 转换byte[]
            byte[] bytes = convertByte(allBytes);
            // 拿到类名
            String className = name.substring(0, name.indexOf("."));
            return defineClass(className, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public byte[] convertByte(byte[] srcByteArray) {
        byte[] res = new byte[srcByteArray.length];
        for (int i = 0; i< srcByteArray.length; i++) {
//            srcByteArray[i] = (byte)(255 - srcByteArray[i]);
            res[i] = (byte)(255 - srcByteArray[i]);
        }
        return res;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader classLoader = new MyClassLoader("D:\\demo\\demo\\demo\\src\\main\\resources");
        Class<?> aClass = classLoader.findClass("Hello.xlass");
        Object o = aClass.newInstance();
        Method hello = aClass.getMethod("hello");
        hello.invoke(o);
    }
}
