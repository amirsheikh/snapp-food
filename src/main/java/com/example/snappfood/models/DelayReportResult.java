package com.example.snappfood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class DelayReportResult {
    private String action;
    private LocalDateTime deliveryDate;
}
