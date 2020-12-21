package com.salestax.domain.carts;

import com.salestax.domain.items.Item;
import com.salestax.domain.taxes.BasicSalesTax;
import com.salestax.domain.taxes.ImportDutyTax;
import com.salestax.utils.Utilities;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingBasket {

    private final LinkedHashMap<Item, Integer> shoppingCartMap = new LinkedHashMap<>();

    public ShoppingBasket() {
    }

    public void put(Item item, int amount) {

        if (!item.isExempt()) {
            item = new BasicSalesTax(item);
        }

        if (item.isImported()) {
            item = new ImportDutyTax(item);
        }

        Integer quantity = this.shoppingCartMap.get(item);
        if (quantity != null) {
            amount = quantity + amount;
        }

        this.shoppingCartMap.put(item, amount);
    }

    public Integer delete(Item item) {
        return this.shoppingCartMap.remove(item);
    }

    public void deleteAll() {
        this.shoppingCartMap.clear();
    }

    public Set<Item> getItems() {
        return this.shoppingCartMap.keySet();
    }

    public Integer getAmount(Item item) {
        return this.shoppingCartMap.get(item);
    }

    public BigDecimal getTaxtotal() {
        BigDecimal taxtotal = new BigDecimal("0");
        for (Item item : shoppingCartMap.keySet()){
            BigDecimal subTotal = item.getFinalPrice().multiply(new BigDecimal(getAmount(item)));
            BigDecimal subInitTotal = item.getPrice().multiply(new BigDecimal(getAmount(item)));
            taxtotal = taxtotal.add( subTotal.subtract(subInitTotal) );
        }
        return taxtotal;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal("0");
        for (Item item : shoppingCartMap.keySet()){
            BigDecimal subTotal = item.getFinalPrice().multiply(new BigDecimal(getAmount(item)));
            total = total.add(subTotal);
        }
        return Utilities.roundPrice(total);
    }

    public void printOrderInput() {
        System.out.println("Order input: ");
        for (Item item : shoppingCartMap.keySet()) {
            System.out.println(shoppingCartMap.get(item) + " " + item.getName() + " at " + item.getPrice());
        }
        System.out.println();
    }

    public void printSummaryOrderResults() {
        BigDecimal taxtotal = new BigDecimal("0");
        BigDecimal total = new BigDecimal("0");
        System.out.println("Order results: ");
        Set<Item> taxedItems = getItems();
        for (Item item : taxedItems){
            BigDecimal subTotal = item.getFinalPrice().multiply(new BigDecimal(getAmount(item)));
            BigDecimal subInitTotal = item.getPrice().multiply(new BigDecimal(getAmount(item)));
            taxtotal = taxtotal.add( subTotal.subtract(subInitTotal) );
            total = total.add(subTotal);
            System.out.println(getAmount(item) + " " + item.getName() + ": " + subTotal);
        }
        total = Utilities.roundPrice(total);
        System.out.println("Sales Taxes: "+ taxtotal);
        System.out.println("Total: "+ total);
        System.out.println();
    }

}
