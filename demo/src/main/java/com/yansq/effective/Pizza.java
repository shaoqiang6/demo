package com.yansq.effective;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author yanshaoqiang
 * @date 2019/1/20 15:20
 */
public abstract class Pizza {
    public enum Topping{
        /** pizza 顶部枚举*/
        HAM, ONION, PEPPER;
    }
    final Set<Topping> toppings;
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(topping);
            return self();
        }
        abstract Pizza build();
        protected abstract T self();
    }
    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone();
    }

}
