package com.geekbrains.book.store.utils;

import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class Utils {

    public static String paramsMapToFilteringString(MultiValueMap<String, String> params) {
        return params.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(val -> Map.entry(entry.getKey(), val)))
                .map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");

    }

    private static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
