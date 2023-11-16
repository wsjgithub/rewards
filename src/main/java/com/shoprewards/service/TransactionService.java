package com.shoprewards.service;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    public Optional<Transaction> findById(long id);
    public Transaction save(Transaction c);
    public List<Transaction> findAll();
    public void deleteById(long id);
}
