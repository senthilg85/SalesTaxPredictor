package com.salestax.domain.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ItemTest {

    @Test
    public void testIsExempt_BookAsInput_ShouldReturnTrue() {
        Item item = new RealItem("book", new BigDecimal("10"));
        Assertions.assertTrue(item.isExempt());
    }

    @Test
    public void testIsExempt_MusicCdAsInput_ShouldReturnFalse() {
        Item item = new RealItem("music CD", new BigDecimal("20"));
        Assertions.assertFalse(item.isExempt());
    }

    @Test
    public void testGetItemType_ChocolateBarAsInput_ShouldReturnFood() {
        Item item = new RealItem("chocolate bar", new BigDecimal("2"));
        Assertions.assertEquals(ItemType.FOOD, item.getItemType());
    }

    @Test
    public void testGetItemOrigin_ImportedComicAsInput_ShouldReturnImported() {
        Item item = new RealItem("imported comic", new BigDecimal("20"));
        Assertions.assertEquals(ItemOrigin.IMPORTED, item.getItemOrigin());
    }

}
