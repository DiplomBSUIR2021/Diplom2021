package com.diploma.demo.view.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Calendar;

public class TimeUtils {

    public static long localeDateToTimeStamp(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().getLong(ChronoField.INSTANT_SECONDS) * 1000;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
