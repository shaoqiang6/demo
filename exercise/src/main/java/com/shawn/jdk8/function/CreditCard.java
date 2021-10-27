package com.shawn.jdk8.function;

import lombok.Data;

/**
 * @author yanshaoqiang
 * @date 2018/12/22 14:22
 */
@Data
class CreditCard {
    private Integer account;

    @Override
    public boolean equals(Object obj){

        return obj instanceof CreditCard  && ((CreditCard) obj).getAccount().equals(this.account);
    }


}
