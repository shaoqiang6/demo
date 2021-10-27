package yansq.jdk8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

/**
 * @author yanshaoqiang
 * @date 2018/11/1 14:52
 */


public class StudyTime {
    
    @Test
    public void fun() throws Exception{
        System.out.println("1111111111111111");
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        System.out.println(Duration.between(end,start).toMillis());
    }
}
