package com.shoprewards;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.sql.Timestamp;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;
    @Test
    public void testSave(){
        Customer c = new Customer("name name", "username1");
        Transaction t = new Transaction(123, Timestamp.valueOf("2007-09-23 10:10:10.0"), c);
        Transaction saved = transactionService.save(t);
        Assertions.assertEquals(saved.getValue(), 123);

    }
}
