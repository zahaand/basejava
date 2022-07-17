package com.zahaand.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String format(LocalDate localDate) {
        if (localDate == null) return "";
        return localDate.equals(NOW) ? "Now" : localDate.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parse(String date) {
        if (date.isEmpty()) return NOW;
        YearMonth yearMonth = YearMonth.parse(date, DATE_TIME_FORMATTER);
        return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
    }
}
