package com.diploma.demo.view.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

public class TimeUtils {

    public static long localeDateToTimeStamp(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().getLong(ChronoField.INSTANT_SECONDS) * 1000;
    }

}
