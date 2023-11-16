package com.shoprewards.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class TimeUtilsTest {
    @Test
    public void testGetYearMonth(){
        Timestamp a = Timestamp.valueOf("1991-12-12 12:12:01");
        Assertions.assertEquals(TimeUtils.getYearMonth(a), "1991-12");
    }
}
