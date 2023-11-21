package com.shoprewards.tools;


import com.github.javafaker.Faker;

import java.sql.Timestamp;
import java.util.Random;

public class RandomStuff {
    public static Timestamp timestamp(){
        long offset = Timestamp.valueOf("2023-09-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2023-11-13 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
    public static int transactionValue(){
        int range = 3000;
        return (int)(Math.random() * range);
    }
    public static String randomName(){
        Faker f = new Faker();
        return f.name().fullName();
    }
    public static String randomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        int targetStringLength = (int) (Math.random() * 20 + 1);
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomName());
    }
}
