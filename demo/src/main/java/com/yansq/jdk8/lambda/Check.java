package yansq.jdk8.lambda;

import java.util.function.Predicate;

/**
 * @author yanshaoqiang
 * @date 2018/12/28 11:25
 */
public interface Check {

    boolean check(Predicate<Integer> value);
//    boolean check(IntPredicate value);
}
