package com.shoprewards.controller;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.exception.CustomerExistsException;
import com.shoprewards.exception.CustomerNotFoundException;
import com.shoprewards.service.CustomerService;
import com.shoprewards.service.TransactionService;
import com.shoprewards.utils.RewardsCalculator;
import com.shoprewards.vo.CustomerVO;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@Validated
@CrossOrigin()
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService cs){
        this.customerService = cs;

    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomerVO getCustomerById(@PathVariable int id){
        return customerService.findWithTransactionsById(id);
    }
    @GetMapping("")
    @ResponseBody
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }
    @PostMapping("")
    @ResponseBody
    public Customer postCustomer(@RequestBody Customer c) throws ValidationException {
        String username = c.getUsername();
        Optional<Customer> opt = customerService.findByUsername(username);
        if(opt.isPresent()){
            throw new CustomerExistsException();
        }
        //        saved.setTransactions(null);
        return customerService.save(c);

    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomerById(@PathVariable int id){
//        Optional<Customer> result = customerService.findById(id);
//        result.orElseThrow(CustomerNotFoundException::new);
//        Customer c = result.get();
        customerService.deleteById(id);

        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    @ResponseBody
    public Customer updateCustomerById(@PathVariable int id, @RequestBody Customer c){
        return customerService.updateById(id, c);
    }


}
