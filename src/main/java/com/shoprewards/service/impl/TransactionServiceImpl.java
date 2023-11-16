package com.shoprewards.service.impl;

import com.shoprewards.dao.TransactionRepository;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionServiceImpl(TransactionRepository tr){
        this.transactionRepository = tr;
    }
    public Optional<Transaction> findById(long id){
        return transactionRepository.findById(id);
    }
    public Transaction save(Transaction c){
        return transactionRepository.save(c);
    }
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }
    public void deleteById(long id){
        transactionRepository.deleteById(id);
    }
}
