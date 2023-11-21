package com.shoprewards.service.impl;

import com.shoprewards.dao.CustomerRepository;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.exception.CustomerNotFoundException;
import com.shoprewards.service.CustomerService;
import com.shoprewards.service.TransactionService;
import com.shoprewards.utils.RewardsCalculator;
import com.shoprewards.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private TransactionService transactionService;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TransactionService ts){
        this.transactionService = ts;
        this.customerRepository = customerRepository;
    }
    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);
    }
    public CustomerVO findWithTransactionsById(long id){
        Optional<Customer> result = customerRepository.findById(id);
        result.orElseThrow(CustomerNotFoundException::new);
        Customer c = result.get();
        List<Transaction> transactions = transactionService.findByCustomerId(c.getId());
        if(!transactions.isEmpty()){
            int total = RewardsCalculator.calculateTotalRewards(transactions);
            int[] monthly = RewardsCalculator.calculateMonthlyRewards(transactions);
            return new CustomerVO(c, transactions, total, monthly);
        }
        return new CustomerVO(c, null, 0, new int[]{0,0,0});

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

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public Customer updateById(long id, Customer newC){
        Optional<Customer> opt = customerRepository.findById(id);
        opt.orElseThrow(CustomerNotFoundException::new);
        Customer old = opt.get();
        old.setName(newC.getName());
        customerRepository.save(old);
        return old;
    }
}
