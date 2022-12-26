package org.crypto;

import org.json.JSONArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

public class Util {

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLocalDateTime(String localDateTime) {
        try {
            LocalDateTime.parse(localDateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    public static String[] toArray(String s) {
        return Stream.of(s.split(",")).toArray(String[]::new);
    }

    public static LocalDateTime toDateTime(String dateTime) {
        String dt = dateTime.replace(" ", "T");
        return isLocalDateTime(dt) ? LocalDateTime.parse(dt) : LocalDateTime.now();
    }

    public static Boolean toBoolean(String value) {
        return isBoolean(value);
    }

    public static  Double toDouble(String value) {
        return isDouble(value) ? Double.parseDouble(value) : 0.00;
    }

    public static Integer toInteger(String value) {
        return isInt(value) ? Integer.parseInt(value) : 0;
    }

    public static Float toFloat(String value) {
        return isFloat(value) ? Float.parseFloat(value) : 0.00f;
    }

    public static Long toLong(String value) {
        return isLong(value) ? Long.parseLong(value) : 0L;
    }

    public static String[] toArrayFrom(JSONArray jsonArray) {
        return Stream.of(jsonArray)
                .map(JSONArray::toString)
                .toArray(String[]::new);
    }

    public static String[] toArrayFrom(String str) {
        if (str.isEmpty()) {
            return new String[1];
        }
        return str.split("::");
    }

}
