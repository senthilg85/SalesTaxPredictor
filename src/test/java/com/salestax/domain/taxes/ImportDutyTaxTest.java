package com.salestax.domain.taxes;

import com.salestax.domain.items.Item;
import com.salestax.domain.items.RealItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ImportDutyTaxTest {

    @Test
    public void testGetFinalPrice_ShouldIncreaseFivePercentOfPrice() {
        Item item = new RealItem("TestedItem", new BigDecimal(200.00));
        Assertions.assertTrue(Math.abs((item.getFinalPrice().subtract(new BigDecimal(200.00)).doubleValue())) == 0.0);

        item = new ImportDutyTax(item);
        Assertions.assertTrue(Math.abs((item.getFinalPrice().subtract(new BigDecimal(210.00)).doubleValue())) == 0.0);
    }

}
