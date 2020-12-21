package com.salestax.domain.carts;

import com.salestax.domain.items.Item;
import com.salestax.domain.items.RealItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ShoppingBasketTest {

    @Test
    public void testGetItemsSize_WithACustomNumberOfProducts_ShouldReturnTheSameSize() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.put(new RealItem("book", new BigDecimal("12.49")), 1);
        shoppingBasket.put(new RealItem("music CD", new BigDecimal("14.99")), 1);
        shoppingBasket.put(new RealItem("chocolate bar", new BigDecimal("0.85")), 1);

        Assertions.assertEquals(3, shoppingBasket.getItems().size());
    }

    @Test
    public void testGetTaxtotal_WithCase001_ShouldReturnOneDotFifty() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.put(new RealItem("book", new BigDecimal("12.49")), 1);
        shoppingBasket.put(new RealItem("music CD", new BigDecimal("14.99")), 1);
        shoppingBasket.put(new RealItem("chocolate bar", new BigDecimal("0.85")), 1);

        Assertions.assertEquals(new BigDecimal("1.50"), shoppingBasket.getTaxtotal());
    }


    @Test
    public void testGetTotal_WithCase001_ShouldReturnTwentyNineDotEightyThree() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.put(new RealItem("book", new BigDecimal("12.49")), 1);
        shoppingBasket.put(new RealItem("music CD", new BigDecimal("14.99")), 1);
        shoppingBasket.put(new RealItem("chocolate bar", new BigDecimal("0.85")), 1);

        Assertions.assertEquals(new BigDecimal("29.83"), shoppingBasket.getTotal());
    }

    @Test
    public void testGetTaxtotal_WithCase001WithDoubleAmountOfItems_ShouldDoubleTheTaxTotal() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.put(new RealItem("book", new BigDecimal("12.49")), 2);
        shoppingBasket.put(new RealItem("music CD", new BigDecimal("14.99")), 2);
        shoppingBasket.put(new RealItem("chocolate bar", new BigDecimal("0.85")), 2);

        Assertions.assertEquals(3, shoppingBasket.getItems().size());
        Assertions.assertEquals(new BigDecimal("3.00"), shoppingBasket.getTaxtotal());
    }

    @Test
    public void testGetTotal_WithCase001WithDoubleAmountOfItems_ShouldDoubleTheTotal() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.put(new RealItem("book", new BigDecimal("12.49")), 2);
        shoppingBasket.put(new RealItem("music CD", new BigDecimal("14.99")), 2);
        shoppingBasket.put(new RealItem("chocolate bar", new BigDecimal("0.85")), 2);

        Assertions.assertEquals(3, shoppingBasket.getItems().size());
        Assertions.assertEquals(new BigDecimal("59.66"), shoppingBasket.getTotal());
    }

    @Test
    public void testGetItemsSize_MismatchEntry_ShouldHaveOnlyOneEntry() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        Item item = new RealItem("book", new BigDecimal("12.49"));
        shoppingBasket.put(item, 2);
        shoppingBasket.put(item, 2);

        Assertions.assertEquals(1, shoppingBasket.getItems().size());
    }

    @Test
    public void testGetItemsSize_MismatchEntry_ShouldUpdateAmountAddingBoth() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        Item item = new RealItem("book", new BigDecimal("12.49"));
        shoppingBasket.put(item,2);
        shoppingBasket.put(item,4);

        Assertions.assertEquals(2 + 4, shoppingBasket.getAmount(item));
    }

    @Test
    public void testGelete_ItemAsInput_ShouldDeleteTheItemFromTheBasket() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        Item item = new RealItem("book", new BigDecimal("12.49"));
        Item secondItem = new RealItem("cd", new BigDecimal("15.55"));
        shoppingBasket.put(item, 1);
        shoppingBasket.put(secondItem, 1);

        shoppingBasket.delete(item);
        Assertions.assertEquals(1, shoppingBasket.getItems().size());
    }

    @Test
    public void testDeleteAll_ShouldClearTheBasketToEmpty() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        Item item = new RealItem("book", new BigDecimal("12.49"));
        Item secondItem = new RealItem("cd", new BigDecimal("15.55"));
        shoppingBasket.put(item, 1);
        shoppingBasket.put(secondItem, 1);

        shoppingBasket.deleteAll();
        Assertions.assertEquals(0, shoppingBasket.getItems().size());
    }

}
