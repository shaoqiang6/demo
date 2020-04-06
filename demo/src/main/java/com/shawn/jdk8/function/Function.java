package yansq.jdk8.function;

/**
 * @author yanshaoqiang
 * @date 2018/12/22 16:13
 */
public interface Function<T,U> {

    U apply(T t);
}
