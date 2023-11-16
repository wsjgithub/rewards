package com.shoprewards.controller;

import com.shoprewards.exception.CustomerNotFoundException;
import com.shoprewards.service.CustomerService;
import com.shoprewards.vo.RewardsMonthAndTotal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
    private final CustomerService customerService;
    public RewardsController(CustomerService cs){
        this.customerService = cs;
    }
    @GetMapping("/{id}")
    public RewardsMonthAndTotal getRewardsById(@PathVariable int id) throws CustomerNotFoundException{
        return customerService.getRewardsById(id);
    }
}
