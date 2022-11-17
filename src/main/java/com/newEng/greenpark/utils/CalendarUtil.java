package com.newEng.greenpark.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Calendar;

public class CalendarUtil {
    public static double getMonthFirstDay() {

        Calendar calendar = Calendar.getInstance();// 获取当前日期

        calendar.add(Calendar.MONTH, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        System.out.println(calendar.getTimeInMillis());

        return calendar.getTimeInMillis();
    }

    public static long getStartTime() {
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return today_start.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }//当天0点
}
