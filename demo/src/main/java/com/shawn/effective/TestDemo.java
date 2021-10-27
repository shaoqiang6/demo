package com.shawn.effective;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author yanshaoqiang
 * @date 2019/1/20 15:00
 */
public class TestDemo {

    @Test
    public void test(){
        NutritionFacts res = new NutritionFacts.Builder(100,20).calorie(10).fat(15).build();
        NutritionFacts res1 = new NutritionFacts.Builder(101,21).calorie(11).fat(11).build();
        System.out.println(res);
        System.out.println(res1);
    }

    @Test
    public void pizzaTest(){
        Pizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.ONION).build();
        System.out.println(pizza);
    }
    @Test
    public void parentTest(){
        Son son = new Son();
        son.setHobby(1);
        son.setName("jim");
//        System.out.println(JSON.toJSONString(son));
    }
}
