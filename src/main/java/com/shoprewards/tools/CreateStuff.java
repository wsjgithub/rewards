package com.shoprewards.tools;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.utils.RewardsCalculator;


import java.sql.Timestamp;


public class CreateStuff {
    public static Transaction randomTransaction(Customer c){
        Timestamp time = RandomStuff.timestamp();
        int value = RandomStuff.transactionValue();
        return new Transaction(value, time, c, RewardsCalculator.calculateRewardsPerValue(value));
    }
    public static Transaction randomTransaction(long customerId){
        Timestamp time = RandomStuff.timestamp();
        int value = RandomStuff.transactionValue();
        return new Transaction(value, time, customerId, RewardsCalculator.calculateRewardsPerValue(value));
    }
//    public static Transaction randomTransaction(){
//        Timestamp time = RandomStuff.timestamp();
//        int value = RandomStuff.transactionValue();
//        return new Transaction(value, time, CreateStuff.randomCustomer().g, RewardsCalculator.calculateRewardsPerValue(value));
//    }

    public static Customer randomCustomer(){
        return new Customer(RandomStuff.randomName(), RandomStuff.randomString());
    }



}
