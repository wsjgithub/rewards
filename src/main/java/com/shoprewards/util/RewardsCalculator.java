package com.shoprewards.util;

import com.shoprewards.entity.Transaction;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class RewardsCalculator {
    private static final long POINTS_OVER_50 = 1;
    private static final long POINTS_OVER_100 = 2;
    public static long calculateTotalRewards(List<Transaction> transactions){
        long result = 0;
        for(Transaction t: transactions){
            int value = t.getValue();
              result += RewardsCalculator.calculateRewardsPerValue(value);
        }
        return result;
    }
    public static long calculateRewardsPerValue(int value){
        long result = 0;
        if(value > 100){
            result += (value - 100) * POINTS_OVER_100 + 50 * POINTS_OVER_50;
        } else if(value > 50){
            result += (value - 50) * POINTS_OVER_50;
        }
        return result;
    }
    public static List[] calculateMonthlyRewards(List<Transaction> transactions){
        Map<String, Long> rewardsPerMonth = new HashMap<>();
        for(Transaction t: transactions){
            Timestamp time = t.getTime();
            String key = TimeUtils.getYearMonth(time);
            if (!rewardsPerMonth.containsKey(key)){
                rewardsPerMonth.put(key, 0L);
            }
            long newRewards = RewardsCalculator.calculateRewardsPerValue(t.getValue());
            rewardsPerMonth.put(key, rewardsPerMonth.get(key) + newRewards);
        }
        List<Long> rewards = new ArrayList<>();
        List<String> months = new ArrayList<>();
        rewardsPerMonth.keySet().stream().sorted().forEach(key->{
            months.add(key);
            rewards.add(rewardsPerMonth.get(key));
        });
        return new List[]{months, rewards};
    }
}
