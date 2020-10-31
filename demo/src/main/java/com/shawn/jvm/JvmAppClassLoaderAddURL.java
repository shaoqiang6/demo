package com.shawn.jvm;

import sun.misc.Launcher;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.stream.Stream;

/**
 * @author yansq
 * @date 2020/10/15
 */
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动");
        Stream.of(urLs).forEach(url -> System.out.println("==>" + url));

        String appPath = "file:\\D:\\demo\\demo\\demo\\src\\main\\java\\com\\shawn";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class<?> localVariableTest = Class.forName("com.shawn.jvm.MyClassLoader");
            System.out.println(localVariableTest);
            MyClassLoader o = (MyClassLoader)localVariableTest.newInstance();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
