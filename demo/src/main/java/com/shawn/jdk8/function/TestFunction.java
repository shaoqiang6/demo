package yansq.jdk8.function;

/**
 * @author yanshaoqiang
 * @date 2018/12/22 16:13
 */
public class TestFunction {
    /**
     * 组合函数
     *
     * @param f1 函数1
     * @param f2 函数2
     * @return 组合函数
     */
    private static Function<Integer, Integer> compose(Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    public static void main(String[] args) {
        Function<Integer, Integer> f1 = x -> x * 3;
        Function<Integer, Integer> f2 = x -> x * x;
        System.out.println(f1.apply(2));
        System.out.println(compose(f1, f2).apply(2));

        Function<Integer, Function<Integer, Integer>> f3 = y -> x -> x + y;
        BinaryOperator add = x->y->x+y;
        System.out.println(add.apply(3).apply(5));
        ComposeOperator squareTriple = x->y->x.apply(y);

        Integer res = squareTriple.apply(f1).apply(2);
        System.out.println(res);
    }


}
