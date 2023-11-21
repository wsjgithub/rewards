package com.shoprewards.serviceTests;

import com.shoprewards.dao.TransactionRepository;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.CustomerService;
import com.shoprewards.service.TransactionService;
import com.shoprewards.tools.CreateStuff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionRepository transactionRepository;
    @Test
    public void testSave(){
        Customer c = new Customer("Harry Potter", "harrypotter");
        Customer saved = customerService.save(c);
        Assertions.assertEquals(saved.getName(), "Harry Potter");
        Assertions.assertEquals(saved.getUsername(), "harrypotter");
    }
    @Test
    public void testFindById(){
        Customer c = new Customer("Tom Cruise", "tmc");
        Customer saved = customerService.save(c);
        Optional<Customer> result = customerService.findById(saved.getId());
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result.get().getId(), saved.getId());
    }

    @Test
    public void testFindAll(){
        List<Customer> result = customerService.findAll();
        Assertions.assertTrue(result.isEmpty());
        int LENGTH = 5;
        for(int i = 0; i < LENGTH; i++){
            Customer c = CreateStuff.randomCustomer();
            customerService.save(c);
        }
        result = customerService.findAll();
        Assertions.assertEquals(result.size(), LENGTH);
    }

    @Test
    public void testDeleteById(){
        Customer c = CreateStuff.randomCustomer();
        customerService.save(c);
        Transaction t = CreateStuff.randomTransaction(c.getId());
        transactionService.save(t);
        customerService.deleteById(c.getId());
        Assertions.assertTrue(customerService.findById(c.getId()).isEmpty());
    }
    @Test
    public void testFindByUsername(){
        Customer c = CreateStuff.randomCustomer();
        Customer saved = customerService.save(c);
        Optional<Customer> found = customerService.findByUsername(c.getUsername());
        Assertions.assertTrue(found.isPresent());
    }
    @Test
    public void testUpdateById(){
        Customer c = CreateStuff.randomCustomer();
        customerService.save(c);
        Customer newC = new Customer("Hello", "You");
        newC = customerService.updateById(c.getId(), newC);
        Assertions.assertEquals(newC.getId(), c.getId());
        Assertions.assertEquals(newC.getName(), "Hello");
    }


}
