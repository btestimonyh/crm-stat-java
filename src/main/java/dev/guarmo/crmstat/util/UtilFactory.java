package dev.guarmo.crmstat.util;

import java.time.format.DateTimeFormatter;

public class UtilFactory {
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter WITHOUT_HOURS_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
