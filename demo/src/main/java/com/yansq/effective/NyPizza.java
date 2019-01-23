package com.yansq.effective;

import lombok.ToString;

import java.util.Objects;

/**
 * @author yanshaoqiang
 * @date 2019/1/20 15:28
 */
@ToString
public class NyPizza extends Pizza {
    public enum Size {
        /**
         * 尺寸
         */
        SMALL, MEDIUM, LARGE
    }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        Pizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    @Override
    public String toString() {

        return "NyPizza(size=" + this.size + ", toppings=" + super.toppings + ")";
    }

}
