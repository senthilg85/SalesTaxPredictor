package com.salestax.utils;

import com.salestax.domain.items.Item;
import com.salestax.domain.items.ItemOrigin;
import com.salestax.domain.items.ItemType;
import com.salestax.domain.items.RealItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class UtilitiesTest {

    @Test
    public void testNearestFivePercent_PriceOverAnExactTen_ShouldReturnTheNearestFivePct() {
        BigDecimal expected = new BigDecimal("11.85");
        BigDecimal actual = Utilities.nearestFivePercent(new BigDecimal("11.8125"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNearestFivePercent_AnExactTenPrice_ShouldReturnTheSame() {
        BigDecimal expected = new BigDecimal("11.80");
        BigDecimal actual = Utilities.nearestFivePercent(new BigDecimal("11.80"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRoundPrice_AmountNotReachTheMiddle_ShouldRoundToDown() {
        BigDecimal expected = new BigDecimal("11.81");
        BigDecimal actual = Utilities.roundPrice(new BigDecimal("11.8125"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRoundPrice_AmountExactlyInTheMiddle_ShouldRoundToUp() {
        BigDecimal expected = new BigDecimal("11.82");
        BigDecimal actual = Utilities.roundPrice(new BigDecimal("11.815"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRoundPrice_AmountOvercomeTheMiddle_ShouldRoundToUp() {
        BigDecimal expected = new BigDecimal("11.85");
        BigDecimal actual = Utilities.roundPrice(new BigDecimal("11.849"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateItemType_BookItemAsInput_ShouldReturnBooks() {
        Item item = new RealItem("book", new BigDecimal("25"));

        Assertions.assertEquals(ItemType.BOOKS, Utilities.calculateItemType(item));
    }

    @Test
    public void testCalculateItemType_ChocolateBarItemAsInput_ShouldReturnFood() {
        Item item = new RealItem("chocolate bar", new BigDecimal("3"));

        Assertions.assertEquals(ItemType.FOOD, Utilities.calculateItemType(item));
    }

    @Test
    public void testCalculateItemType_HeadachePillsItemAsInput_ShouldReturnMedical() {
        Item item = new RealItem("packet of headache pills", new BigDecimal("10"));

        Assertions.assertEquals(ItemType.MEDICAL, Utilities.calculateItemType(item));
    }

    @Test
    public void testCalculateItemType_MusicCdItemAsInput_ShouldReturnOther() {
        Item item = new RealItem("music CD", new BigDecimal("15"));

        Assertions.assertEquals(ItemType.OTHER, Utilities.calculateItemType(item));
    }

    @Test
    public void testCalculateItemOrigin_NonImportedItemAsInput_ShouldReturnLocal() {
        Item item = new RealItem("item", new BigDecimal("10"));

        Assertions.assertEquals(ItemOrigin.LOCAL, Utilities.calculateItemOrigin(item));
    }

    @Test
    public void testCalculateItemOrigin_ImportedItemAsInput_ShouldReturnImported() {
        Item item = new RealItem("imported item", new BigDecimal("10"));

        Assertions.assertEquals(ItemOrigin.IMPORTED, Utilities.calculateItemOrigin(item));
    }

    @Test
    public void testGetFileNames_WithArgs_ShouldHaveSameSizeThatNumberOfArgs() throws IOException {
        String[] inputArgs = new String[]{"a", "b"};
        List<String> fileNames = Utilities.getFileNames(inputArgs);

        Assertions.assertNotNull(fileNames);
        Assertions.assertEquals(inputArgs.length, fileNames.size());

        int idx = 0;
        for(String inputArg: inputArgs) {
            Assertions.assertEquals(
                    Paths.get(Utilities.DATA_FOLDER_PATH, inputArg).toString(),
                    fileNames.get(idx)
            );
            idx++;
        }
    }

    @Test
    public void testGetFileNames_WithEmptyInputArgs_ShouldHaveSameSizeThatCountFilesInDataFolder() throws IOException {
        String[] inputArgs = new String[] {};
        List<String> fileNames = Utilities.getFileNames(inputArgs);

        Assertions.assertNotNull(fileNames);
        Assertions.assertEquals(Files.list(Paths.get(Utilities.DATA_FOLDER_PATH)).count(), fileNames.size());
    }

    @Test
    public void testProcessData_WithInputOfCaseOne_ShouldBeProcessed() {
        List<String> fileNames = Collections.singletonList(Paths.get(Utilities.DATA_FOLDER_PATH, "001.txt").toString());
        Utilities.processData(fileNames);
    }

}
