package br.com.tspaschoal.auttaskit.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {
    public static String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMddhhmmss");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);
    }
}
