package com.salestax.domain.taxes;

import com.salestax.domain.items.Item;
import com.salestax.domain.items.ItemOrigin;
import com.salestax.domain.items.ItemType;
import com.salestax.utils.Utilities;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Tax implements Item {

    protected Item item;
    protected BigDecimal rate;

    public Tax(Item item, BigDecimal rate) {
        this.item = item;
        this.rate = rate;
    }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public BigDecimal getPrice() {
        return item.getPrice();
    }

    @Override
    public ItemType getItemType() {
        return item.getItemType();
    }

    @Override
    public ItemOrigin getItemOrigin() {
        return item.getItemOrigin();
    }

    @Override
    public BigDecimal getFinalPrice() {
        BigDecimal salesTaxValue = Utilities.nearestFivePercent(this.item.getPrice().multiply(this.getRate()));
        return Utilities.roundPrice(item.getFinalPrice().add(salesTaxValue));
    }

    @Override
    public Boolean isImported() {
        return item.isImported();
    }

    @Override
    public Boolean isExempt() {
        return item.isExempt();
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(item, tax.item) &&
                Objects.equals(rate, tax.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, rate);
    }

}