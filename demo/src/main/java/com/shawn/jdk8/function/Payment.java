package com.shawn.jdk8.function;

import lombok.Getter;

/**
 * @author yanshaoqiang
 * @date 2018/12/22 14:18
 */
@Getter
class Payment {
    private final CreditCard creditCard;
    private final int amount;

    Payment(CreditCard creditCard, int amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }
    public Payment combine(Payment payment) throws IllegalAccessException{
        if(creditCard.equals(payment.getCreditCard())){
            return new Payment(creditCard,payment.getAmount() + this.amount);
        }
        throw new IllegalAccessException("card don't match");
    }


}
