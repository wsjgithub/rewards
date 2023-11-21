package com.shoprewards.serviceTests;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.CustomerService;
import com.shoprewards.service.TransactionService;
import com.shoprewards.tools.CreateStuff;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;
    @Autowired
    CustomerService customerService;
    @Test
    public void testSave(){
        Customer c = new Customer("name name", "username1");
        customerService.save(c);
        Customer b = new Customer("name name", "username1");
        Transaction t1 = CreateStuff.randomTransaction(c.getId());
        Transaction t2 = CreateStuff.randomTransaction(c.getId());
        transactionService.save(t1);
        transactionService.save(t2);

    }

    @Test
    public void testFindById(){
        Transaction t = createTransaction();
        int value = t.getValue();
        Optional<Transaction> found = transactionService.findById(t.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(value, found.get().getValue());
    }
    @Test
    public void testDeleteById(){
        Transaction t = createTransaction();
        Transaction t1 = CreateStuff.randomTransaction(t.getCustomerId());
        transactionService.save((t1));
        transactionService.deleteById(t.getId());
    }
    @Test
    public void testUpdateById(){
        Transaction t = createTransaction();
        Timestamp time = Timestamp.valueOf("2011-10-02 18:48:05.123");
        Transaction newT = new Transaction(100, time, 1, 200);
        Transaction updated = transactionService.updateById(t.getId(), newT);
        Assertions.assertEquals(updated.getValue(), 100);
        Assertions.assertEquals(updated.getTime(), time);
    }

    private Transaction createTransaction(){
        Customer c = CreateStuff.randomCustomer();
        customerService.save(c);
        Transaction t = CreateStuff.randomTransaction(c.getId());
        return transactionService.save(t);
    }


}
