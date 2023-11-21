package com.shoprewards.controller;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.CustomerService;
import com.shoprewards.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import com.shoprewards.tools.CreateStuff;

import java.util.Random;

@RestController
@RequestMapping("/test")
@CrossOrigin()
public class TestController {
    private final CustomerService customerService;
    private final TransactionService transactionService;
    public TestController(CustomerService cs, TransactionService ts){

        this.customerService = cs;
        this.transactionService = ts;
    }
    @PostMapping("/populate")
    @ResponseBody
    public String populateTestData() {
        int CUSTOMER_LENGTH = 20;
        for(int i = 0; i < CUSTOMER_LENGTH; i++){
            Customer c = CreateStuff.randomCustomer();
            Customer savedC = customerService.save(c);
            Random rand = new Random();
            int TRANSACTION_LENGTH = rand.nextInt((15 - 4) + 1) + 4;
            for(int j = 0; j < TRANSACTION_LENGTH; j++){
                Transaction t = CreateStuff.randomTransaction(savedC.getId());
                transactionService.save(t);
            }

        }
        return "Success";
    }
}
