package com.salestax.domain.items;

import java.math.BigDecimal;

public interface Item {

    String getName();
    BigDecimal getPrice();
    ItemType getItemType();
    ItemOrigin getItemOrigin();
    BigDecimal getFinalPrice();
    Boolean isImported();
    Boolean isExempt();

}
