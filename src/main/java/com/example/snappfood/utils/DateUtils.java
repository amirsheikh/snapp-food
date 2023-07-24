package com.example.snappfood.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getXMinAfterDate(Date date, int xMin) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, xMin);
        return calendar.getTime();
    }

    public static long getDatesDiffInMin(Date higherDate, Date lowerDate) {
        return (higherDate.getTime() - lowerDate.getTime()) / (60*1000);
    }

    public static LocalDateTime toLocal(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
