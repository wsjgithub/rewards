package com.shoprewards.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

public class TimeUtils {
    public static int[] getYearMonth(Timestamp time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return new int[]{year, month};
    }

    public static int[][] lastThreeMonths(){
        int[] offsets = {0, 1, 2};
        int[][] ym = new int[offsets.length][2];
        LocalDate now = LocalDate.now(); // 2015-11-24
        for(int i = 0; i < offsets.length; i++){
            LocalDate earlier = now.minusMonths(offsets[i]);
            int month = earlier.getMonthValue();
            int year = earlier.getYear();
            ym[i][0] = year;
            ym[i][1] = month;
        }
        return ym;
    }

    public static void main(String[] args) {
        int[][] a = TimeUtils.lastThreeMonths();
        for(int[] y: a){
            System.out.println(y[0]);
            System.out.println(y[1]);
        }

    }
}
