package com.shoprewards.util;

import com.shoprewards.entity.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RewardsCalculatorTest {

    @Test
    public void testCalculateRewardsPerValue(){
        Assertions.assertEquals(RewardsCalculator.calculateRewardsPerValue(130), 110);
        Assertions.assertEquals(RewardsCalculator.calculateRewardsPerValue(60), 10);
        Assertions.assertEquals(RewardsCalculator.calculateRewardsPerValue(30), 0);
    }
    @Test
    public void testCalculateTotalRewards(){
        int[] values = {130, 60, 30};
        List<Transaction> transactions = new ArrayList<>();
        for (int value : values) {
            Transaction t = new Transaction();
            t.setValue(value);
            transactions.add(t);
        }
        long result = RewardsCalculator.calculateTotalRewards(transactions);
        Assertions.assertEquals(result, 120);
    }

    @Test
    public void testCalculateMonthlyRewards(){
        int[] values = {130, 60, 30};
        String[] dates = {"2020-12-12 12:12:01","1999-01-12 12:12:01", "2023-08-12 12:12:01"};
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Transaction t = new Transaction();
            t.setTime(Timestamp.valueOf(dates[i]));
            t.setValue(values[i]);
            transactions.add(t);
        }
        List[] results = RewardsCalculator.calculateMonthlyRewards(transactions);
        Assertions.assertArrayEquals(results[0].toArray(), new String[]{"1999-01", "2020-12", "2023-08"});
        Assertions.assertArrayEquals(results[1].toArray(), new Long[]{10L, 110L, 0L});
    }
}
