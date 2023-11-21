package com.shoprewards.dao;

import com.shoprewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findTransactionsByCustomerId(long id);
    public void deleteTransactionById(long id);
}
