package com.example.snappfood.component;

public interface MessageProvider {
    String getMessage(String code);
    String getMessage(String code, Object... args);
}
