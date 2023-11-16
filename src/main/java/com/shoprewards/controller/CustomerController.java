package com.shoprewards.controller;

import com.shoprewards.entity.Customer;
import com.shoprewards.exception.CustomerNotFoundException;
import com.shoprewards.service.CustomerService;
import com.shoprewards.vo.CustomerVO;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService cs){
        this.customerService = cs;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Customer getCustomerById(@PathVariable int id){
        Optional<Customer> result = customerService.findById(id);
        result.orElseThrow(CustomerNotFoundException::new);
        CustomerVO res = new CustomerVO();
        return result.get(); // how to get rid of transactions
    }
    @PostMapping()
    public ResponseEntity<String> postCustomer(@RequestBody Customer c) throws ValidationException {
        customerService.save(c);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }


}
