package com.salestax.domain.items;

import com.salestax.utils.Utilities;

import java.math.BigDecimal;
import java.util.Objects;

public class RealItem implements Item {

    private String name;
    private BigDecimal price;
    private ItemType type;
    private ItemOrigin origin;

    public RealItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.type = Utilities.calculateItemType(this);
        this.origin = Utilities.calculateItemOrigin(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public ItemType getItemType() {
        return type;
    }

    @Override
    public ItemOrigin getItemOrigin() {
        return origin;
    }

    @Override
    public BigDecimal getFinalPrice() {
        return price;
    }

    @Override
    public Boolean isImported() {
        return ItemOrigin.IMPORTED.equals(origin);
    }

    @Override
    public Boolean isExempt() {
        return Utilities.EXEMPT_ITEM_TYPES.contains(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealItem realItem = (RealItem) o;
        return Objects.equals(name, realItem.name) &&
                Objects.equals(price, realItem.price) &&
                type == realItem.type &&
                origin == realItem.origin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type, origin);
    }

    @Override
    public String toString() {
        return "com.codechallenge.domain.items.RealItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", origin=" + origin +
                '}';
    }

}
