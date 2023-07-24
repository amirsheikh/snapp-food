package com.example.snappfood.exceptions;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(String format, String ... args) {
        super(setArgs(format, args));
    }

    private static String setArgs(String format, String[] args) {
        for (String arg : args) {
            format = format.replace("{}", arg);
        }
        return format;
    }
}
