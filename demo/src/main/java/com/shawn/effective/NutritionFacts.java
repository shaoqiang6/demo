package com.shawn.effective;

import lombok.ToString;

/**
 * @author yanshaoqiang
 * @date 2019/1/20 14:50
 */
@ToString
public class NutritionFacts {
    private Integer fat;
    private Integer calorie;
    private int servingSize;
    private int serving;

    public static class Builder {
        private Integer fat;
        private Integer calorie;
        private int servingSize;
        private int serving;

        public Builder(int serving, int servingSize) {
            this.serving = serving;
            this.servingSize = servingSize;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder calorie(int calorie) {
            this.calorie = calorie;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        this.fat = builder.fat;
        this.calorie = builder.calorie;
        this.serving = builder.serving;
        this.servingSize = builder.servingSize;
    }

}
