package com.salestax.parser;

import com.salestax.domain.carts.ShoppingBasket;
import com.salestax.domain.items.Item;
import com.salestax.domain.items.RealItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ItemRetriever {

    private static final Pattern ITEM_PATTERN = Pattern.compile("(\\d+)\\s((\\w+\\s)+)at\\s(\\d+.\\d+)");
    private static final int INDEX_AMOUNT = 1;
    private static final int INDEX_NAME = 2;
    private static final int INDEX_PRICE = 4;

    public ItemRetriever() {
    }

    private static Matcher matcherFind(String line) {
        Matcher m = ITEM_PATTERN.matcher(line);
        m.find();
        return m;
    }

    private static AbstractMap.SimpleEntry<Item, Integer> matcherToSimpleEntry(Matcher matcher) {
        String itemName = matcher.group(INDEX_NAME).trim();
        BigDecimal itemPrice = new BigDecimal(matcher.group(INDEX_PRICE));
        RealItem item = new RealItem(itemName, itemPrice);
        return new AbstractMap.SimpleEntry<>(item, Integer.valueOf(matcher.group(INDEX_AMOUNT)));
    }

    public ShoppingBasket read(String fileName) {

        ShoppingBasket shoppingBasket = new ShoppingBasket();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.filter(line -> Pattern.matches(ITEM_PATTERN.pattern(), line))
                    .map(ItemRetriever::matcherFind)
                    .map(ItemRetriever::matcherToSimpleEntry)
                    .forEach(x -> shoppingBasket.put(x.getKey(), x.getValue()));
            /*
            I use shoppingBasket.put method,
            because it has internally a LinkedHashMap that guarantees the insertion order
            and put method has the logic to process taxes decorators, etc.
            */

        } catch (IOException e) {
            System.out.println("An IOException has occurred:");
            System.out.println(e.getMessage());
        }

        return shoppingBasket;
    }

}
