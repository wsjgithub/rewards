package com.shoprewards.service;

import com.shoprewards.entity.Customer;
import com.shoprewards.vo.RewardsMonthAndTotal;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    public Optional<Customer> findById(long id);
    public Customer save(Customer c);
    public List<Customer> findAll();
    public void deleteById(long id);
    public RewardsMonthAndTotal getRewardsById(long id);

}
