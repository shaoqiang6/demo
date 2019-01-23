package com.yansq.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author yanshaoqiang
 */
public class AviatorTest {
    /**
     * 加法方法名
     */
    private static String ADD_LAMBDA = "add_lambda";

    public static void main(String[] args) {
        String name = "jim";
        Map<String, Object> env = new HashMap<>(4);
        env.put("name", name);
        String result = (String) AviatorEvaluator.execute("'hello ' + name ", env);
        System.out.println(result);
        System.out.println(AviatorEvaluator.execute(" 'a\"b'"));
        System.out.println(AviatorEvaluator.execute(" \"a\\\"b\""));
        System.out.println(AviatorEvaluator.execute("1 + string.length('hello')"));
        System.out.println(AviatorEvaluator.execute("string.contains('test',string.substring('hello',1,2))"));
        System.out.println(AviatorEvaluator.execute(String.format("(lambda(a) -> lambda(b) -> lambda(c) -> a + b + c end end end)(%s)(%s)(%s)", 1, 2, 3)));
        System.out.println("-----------------------");
        // lambda接收多个变量直接调用
        System.out.println(AviatorEvaluator.execute(String.format("(lambda(a,b,c) ->  a + b + c  end)(%s,%s,%s)", 1, 2, 3)));
        // 自定义函数 , 重写call方法->具体的函数实现, getName 方法对函数命名
        defineFunction();
        System.out.println(">>>>>>可变参数>>>>>");
        // 可变参数 函数定义
        AviatorEvaluator.addFunction(
                new AbstractVariadicFunction() {
                    @Override
                    public AviatorObject variadicCall(Map<String, Object> map, AviatorObject... args) {
                        if(args != null){
                            for (AviatorObject arg :args){
                                if(null != arg){
                                    return arg;
                                }
                            }
                        }
                        return new AviatorString(null);
                    }

                    @Override
                    public String getName() {
                        return "firstNonNull";
                    }
                }
        );
        System.out.println(AviatorEvaluator.execute("firstNonNull(1,2)"));

        System.out.println("lambda 函数-------------");
        // lambda 定义函数
        AviatorEvaluator.defineFunction("addLambda","lambda(a,b) ->a * b end ");
        System.out.println(AviatorEvaluator.execute("addLambda(2,10)"));
        // 访问数组 集合
        listMap();
        System.out.println("三元表达式----");
        System.out.println(AviatorEvaluator.execute("(lambda(a) -> a>0?'yes':'no' end)(1) "));
        System.out.println(AviatorEvaluator.execute("nil == nil"));
        System.out.println(AviatorEvaluator.execute("1> nil").getClass().getTypeName());
        System.out.println(AviatorEvaluator.execute("'' + nil"));
        bean();
//        Long result = (Long)AviatorEvaluator.execute("1+2+3");
//        System.out.println(result);
        // aviator支持的数字为 long 和 double
        // 解析浮点时解析为bigDecimal 否则 为double
//        AviatorEvaluator.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL,true);
//        BigDecimal res = (BigDecimal)AviatorEvaluator.execute("1+2.2");
//        System.out.println(res);

//        AviatorEvaluator.addFunction(new AddFunction());
//        System.out.println(AviatorEvaluator.execute("add(1,2)"));
//        AviatorEvaluator.defineFunction(ADD_LAMBDA,"lambda(x,y)->x + y end");
//        System.out.println(AviatorEvaluator.exec(ADD_LAMBDA + "(1,2)"));
//
//        String expression = "a-(b-c)>100";
//        Map<String,Object> env = new HashMap<>(4);
//        env.put("a",100);
//        env.put("b",20);
//        env.put("c",30);
//        Expression res = AviatorEvaluator.compile(expression);
//        System.out.println(res.execute(env));
//        String expression2 = "(lambda(a,b,c)-> a-(b-c) end)(100,20,30)";
//
//        Expression res2 = AviatorEvaluator.compile(expression2);
//        System.out.println("env2:-----> "+res2.execute());
//        Object obj = AviatorEvaluator
//                .exec("(lambda (x) -> lambda(y) -> lambda(z) -> x + y + z end end end)(1)(2)(3)");
//        System.out.println(obj);


    }
    private static void bean(){
        AviatorBean ab = new AviatorBean(1, BigDecimal.ONE,new Date());
        Map<String, Object> map = new HashMap<>(2);
        map.put("bean",ab);
        System.out.println("-----bean------");
        System.out.println(AviatorEvaluator.execute("bean.prize>0 ",map));
    }
    private static void defineFunction(){
        System.out.println("自定义函数");
        AviatorEvaluator.addFunction(
                new AbstractFunction() {
                    @Override
                    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
                        Number left = FunctionUtils.getNumberValue(arg1, env);
                        Number right = FunctionUtils.getNumberValue(arg2, env);
                        return new AviatorDouble(left.doubleValue() + right.doubleValue());
                    }
                    @Override
                    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
                        Number left = FunctionUtils.getNumberValue(arg1, env);
                        return new AviatorDouble(left.doubleValue() * 10);
                    }
                    @Override
                    public String getName() {
                        return "add";
                    }
                }
        );
        System.out.println(AviatorEvaluator.execute("add(10,2)"));
        System.out.println(AviatorEvaluator.execute("add(add(10,2))"));
    }
    private static void listMap(){
        System.out.println("访问数组 集合-------");
        final List<String> list = new ArrayList<>(2);
        list.add("hello");
        list.add("world");
        final Map<String, Date> dateMap = new HashMap<>(4);
        dateMap.put("today",new Date());
        final int[] intArray = new int[3];
        intArray[0] = 1;
        intArray[1] = 2;
        intArray[2] = 3;
        Map<String,Object> listMapEnv = new HashMap<>(4);
        listMapEnv.put("list",list);
        listMapEnv.put("mmap",dateMap);
        listMapEnv.put("array",intArray);
        System.out.println(AviatorEvaluator.execute("list[0] + \" \" + list[1]",listMapEnv));
    }


}
