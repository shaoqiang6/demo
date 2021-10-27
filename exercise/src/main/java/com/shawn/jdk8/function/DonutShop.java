package com.shawn.jdk8.function;

/**
 * @author yanshaoqiang
 * @date 2018/12/22 14:21
 */
public class DonutShop {
    public static Tuple<Donut,Payment> buyDonut(CreditCard creditCard){
        Donut donut = new Donut();
        Payment payment = new Payment(creditCard,10);
        return new Tuple<>(donut,payment);
    }

}
