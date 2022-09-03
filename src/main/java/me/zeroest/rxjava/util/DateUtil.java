package me.zeroest.rxjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getNowDate() {
        return LocalDateTime.now().format(YYYY_MM_DD_HH_MM_SS);
    }
}
