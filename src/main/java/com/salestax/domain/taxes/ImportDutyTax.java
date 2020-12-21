package com.salestax.domain.taxes;

import com.salestax.domain.items.Item;

import java.math.BigDecimal;

public class ImportDutyTax extends Tax {

    private static final BigDecimal RATE = new BigDecimal("0.05");

    public ImportDutyTax(Item item) {
        super(item, ImportDutyTax.RATE);
    }

    @Override
    public String toString() {
        return "com.codechallenge.domain.taxes.ImportDutyTax{" +
                "item=" + item +
                ", rate=" + rate +
                '}';
    }

}