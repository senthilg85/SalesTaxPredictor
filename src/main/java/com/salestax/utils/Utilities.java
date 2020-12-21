package com.salestax.utils;

import com.salestax.domain.items.Item;
import com.salestax.domain.items.ItemOrigin;
import com.salestax.domain.items.ItemType;
import com.salestax.parser.ItemRetriever;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Utilities {

    private final static String IMPORTED_ITEM_KEYWORD = "imported";
    private final static ItemType DEFAULT_ITEM_TYPE = ItemType.OTHER;

    private final static LinkedHashMap<ItemType, Set<String>> ITEM_TYPES_BY_KEYWORDS = new LinkedHashMap<>();
    static {
        ITEM_TYPES_BY_KEYWORDS.put(ItemType.BOOKS, new HashSet<>(Arrays.asList("book")));
        ITEM_TYPES_BY_KEYWORDS.put(ItemType.FOOD, new HashSet<>(Arrays.asList("chocolate bar", "imported box of chocolates", "box of imported chocolates")));
        ITEM_TYPES_BY_KEYWORDS.put(ItemType.MEDICAL, new HashSet<>(Arrays.asList("packet of headache pills")));
    }

    public final static Set<ItemType> EXEMPT_ITEM_TYPES = new HashSet<>();
    static {
        EXEMPT_ITEM_TYPES.add(ItemType.BOOKS);
        EXEMPT_ITEM_TYPES.add(ItemType.FOOD);
        EXEMPT_ITEM_TYPES.add(ItemType.MEDICAL);
    }

    public static String DATA_FOLDER_PATH = "data";

    public static ItemType calculateItemType(Item item) {

        Optional<Map.Entry<ItemType, Set<String>>> optionalItemType = ITEM_TYPES_BY_KEYWORDS
                .entrySet()
                .stream()
                .filter(l -> l.getValue().contains(item.getName()))
                .findFirst();

        return (optionalItemType.isPresent() ? optionalItemType.get().getKey() : DEFAULT_ITEM_TYPE);
    }

    public static ItemOrigin calculateItemOrigin(Item item) {
        ItemOrigin origin = ItemOrigin.LOCAL;
        if (item.getName().contains(Utilities.IMPORTED_ITEM_KEYWORD))
            origin = ItemOrigin.IMPORTED;

        return origin;
    }

    static public BigDecimal nearestFivePercent(BigDecimal amount) {
        double dAmount = amount.doubleValue();
        return BigDecimal.valueOf(Math.ceil(dAmount * 20) / 20).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal roundPrice(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static List<String> getFileNames(String[] args) throws IOException {
        List<String> fileNames;
        if (args.length == 0) {
            fileNames = Files.list(Paths.get(DATA_FOLDER_PATH))
                    .sorted()
                    .map(Path::toString).collect(Collectors.toList());
        } else {
            fileNames = Arrays.stream(args)
                    .map(arg -> Paths.get(DATA_FOLDER_PATH, arg).toString()).collect(Collectors.toList());
        }

        return fileNames;
    }

    public static void processData(List<String> fileNames) {
        fileNames.stream()
                .map(fileName -> new ItemRetriever().read(fileName))
                .forEach(cart -> {
                    cart.printOrderInput();
                    cart.printSummaryOrderResults();
                });
    }

}
