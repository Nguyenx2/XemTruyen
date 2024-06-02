package com.example.xemtruyen.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ConvertUtils {
    public static String convertToSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String slug = pattern.matcher(normalized)
                .replaceAll("")
                .replaceAll(" ", "-")
                .toLowerCase();
                return slug;
    }
}
