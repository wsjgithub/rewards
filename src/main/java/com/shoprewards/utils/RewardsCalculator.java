package com.shoprewards.utils;

import com.shoprewards.entity.Transaction;

import java.util.*;

public class RewardsCalculator {
    private static final int POINTS_OVER_50 = 1;
    private static final int POINTS_OVER_100 = 2;
    public static int calculateTotalRewards(List<Transaction> transactions){
        int result = 0;
        for(Transaction t: transactions){
            int value = t.getValue();
              result += t.getPoints();
        }
        return result;
    }
    public static int calculateRewardsPerValue(int value){
        int result = 0;
        if(value > 100){
            result += (value - 100) * POINTS_OVER_100 + 50 * POINTS_OVER_50;
        } else if(value > 50){
            result += (value - 50) * POINTS_OVER_50;
        }
        return result;
    }

    public static int[] calculateMonthlyRewards(List<Transaction> transactions){
        int[][] lastThreeMonths = TimeUtils.lastThreeMonths();
        int[] result = new int[3];
        for(Transaction t: transactions){
            for(int i = 0; i < result.length; i++){
                if(Arrays.equals(lastThreeMonths[i], TimeUtils.getYearMonth(t.getTime()))){
                    result[i] += t.getPoints();
                }
            }
        }
        return result;
    }



}
