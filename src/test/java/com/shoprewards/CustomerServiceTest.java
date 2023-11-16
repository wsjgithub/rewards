package com.shoprewards;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shoprewards.entity.Customer;
import com.shoprewards.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;

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
}
