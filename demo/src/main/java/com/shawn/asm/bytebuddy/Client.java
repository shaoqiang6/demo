package com.shawn.asm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yansq
 * @date 2020/12/16
 */
public class Client {

    public static void main(String[] args) throws Exception{
//        Singable singable = create();
//        System.out.println(singable);
//        singable.sing("once more");
//        System.out.println("---------------------");
//        Singable singable2 = create2();
//        System.out.println(singable2);
//        singable2.sing("once more");

        Set<Long> set1 = new HashSet<>();
        set1.add(1L);
        Set set2 = set1;
        set2.add("asd");
        set1.add(2L);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        Object[] objects = set1.toArray();
        Set<String> className = Stream.of(objects).map(t -> t.getClass().getName()).collect(Collectors.toSet());
        System.out.println(className);
        Set<String> collect = set1.stream().map(t -> t.getClass().getName()).collect(Collectors.toSet());
        System.out.println(collect);

    }

    private static Singable create() throws Exception {
        return (Singable) new ByteBuddy()
                .subclass(Object.class)
                .implement(Singable.class)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new SingableInvocationHandler(new SingerMichael())))
                .make()
                .load(Client.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    private static Singable create2() throws Exception {
        return new ByteBuddy()
                .subclass(SingerMichael.class)
                .implement(Singable.class)
                .method(ElementMatchers.named("sing"))
                .intercept(MethodDelegation.to(new SingerAgentInterceptor()))
                .make()
                .load(Client.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    public static class SingableInvocationHandler implements InvocationHandler {
        private Object target;

        public SingableInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("byte buddy proxy before sing ");
            Object ret = method.invoke(target, args);
            System.out.println("byte buddy proxy after sing ");
            return ret;
        }
    }

    public static class SingerAgentInterceptor {

        public Object interceptor(@This Object proxy, @Origin Method method,
                                  @SuperMethod Method superMethod,
                                  @AllArguments Object[] args) throws Exception {
            System.out.println("byte buddy delegate proxy2 before sing ");
            Object ret = superMethod.invoke(proxy, args);
            System.out.println("byte buddy delegate proxy2 after sing ");
            return ret;
        }
    }



}
