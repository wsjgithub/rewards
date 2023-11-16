package com.shoprewards.service.impl;

import com.shoprewards.dao.CustomerRepository;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.exception.CustomerNotFoundException;
import com.shoprewards.service.CustomerService;
import com.shoprewards.util.RewardsCalculator;
import com.shoprewards.vo.RewardsMonthAndTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);
    }
    public Customer save(Customer c){
        return customerRepository.save(c);
    }
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    public void deleteById(long id){
        customerRepository.deleteById(id);
    }
    public RewardsMonthAndTotal getRewardsById(long id){
        Optional<Customer> c = customerRepository.findById(id);
        if(c.isEmpty()){
            throw new CustomerNotFoundException();
        }
        Set<Transaction> transactions = c.get().getTransactions();
        RewardsMonthAndTotal result = new RewardsMonthAndTotal();
        List<Transaction> transactionList = new ArrayList<Transaction>(transactions);
        long total = RewardsCalculator.calculateTotalRewards(transactionList);
        result.setTotal(total);
        List[] monthly = RewardsCalculator.calculateMonthlyRewards(transactionList);
        result.setMonths(monthly[0]);
        result.setRewardsPerMonth((monthly[1]));
        return result;

    }
}
