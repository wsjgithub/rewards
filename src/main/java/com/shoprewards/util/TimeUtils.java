package com.shoprewards.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeUtils {
    public static String getYearMonth(Timestamp time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder result = new StringBuilder();
        result.append(year);
        result.append("-");
        if(month < 10){
            result.append(0);
        }
        result.append(month);
        return result.toString();
    }
}
