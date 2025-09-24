package org.bany.utils;

import java.util.List;
import java.util.function.Function;

public class FindElements {
    public static <T> T findByString(List<T> items, String searchStr, Function<T, String> extractStr){
        return items.stream()
                .filter(item -> extractStr.apply(item).equals(searchStr))
                .findFirst()
                .orElse(null);
    }
}
