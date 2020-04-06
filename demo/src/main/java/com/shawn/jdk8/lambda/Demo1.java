package yansq.jdk8.lambda;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

/**
 * @author yanshaoqiang
 * @date 2018/12/27 19:48
 */
public class Demo1 {

    @Test
    public void fun(){
//        Function<Integer,Long> t = x->(long)(x+5);
//        System.out.println(t.apply(5));

//        Check check = x->x.test(5);
//        System.out.println(check.check(t->t>1));
        Check check = x->x.test(2);
    }
    @Test
    public void fun1(){
        List<Integer> list = Stream.of(1,2,3).collect(Collectors.toList());
        assertEquals(list, asList(1,2,3));
        List<String> list1 = Stream.of("a","b","hello").map(String::toUpperCase).collect(Collectors.toList());
        assertEquals(list1,asList("A","B","HELLO"));
        List<Integer> list2 = Stream.of("1","2","3").map(Integer::valueOf).collect(Collectors.toList());
        assertEquals(list2,asList(1,2,3));
        int count = list2.stream().reduce(0,(sum,e)->sum + e);
        assertEquals(6,count);
        List<Integer> list3 = Stream.of(asList(1,2),asList(3,4,5)).flatMap(List::stream).collect(Collectors.toList());
        assertEquals(list3,asList(1,2,3,4,5));


    }
}
