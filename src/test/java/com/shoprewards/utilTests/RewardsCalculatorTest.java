package com.shoprewards.utilTests;

import com.shoprewards.entity.Transaction;
import com.shoprewards.utils.RewardsCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
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
        int[] values = {130, 60, 60};
        String[] dates = {"2023-11-12 12:12:01","2023-11-1 12:12:01", "2023-10-12 12:12:01"};
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Transaction t = new Transaction();
            t.setTime(Timestamp.valueOf(dates[i]));
            t.setValue(values[i]);
            t.setPoints(RewardsCalculator.calculateRewardsPerValue(values[i]));
            transactions.add(t);
        }
        int[] result = RewardsCalculator.calculateMonthlyRewards(transactions);
        Assertions.assertArrayEquals(new int[]{120, 10, 0}, result);

    }
}
