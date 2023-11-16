package com.shoprewards.dao;

import com.shoprewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
