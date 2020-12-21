package com.salestax.domain.taxes;

import com.salestax.domain.items.Item;

import java.math.BigDecimal;

public class BasicSalesTax extends Tax {

    private static final BigDecimal RATE = new BigDecimal("0.1");

    public BasicSalesTax(Item item) {
        super(item, BasicSalesTax.RATE);
    }

    @Override
    public String toString() {
        return "com.codechallenge.domain.taxes.BasicSalesTax{" +
                "item=" + item +
                ", rate=" + rate +
                '}';
    }

}
