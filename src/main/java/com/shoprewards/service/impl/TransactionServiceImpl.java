package com.shoprewards.service.impl;

import com.shoprewards.dao.TransactionRepository;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.exception.TransactionNotFoundException;
import com.shoprewards.service.TransactionService;
import jakarta.transaction.Transactional;
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
    public Transaction save(Transaction t){
        return transactionRepository.save(t);
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }
    @Transactional
    public void deleteById(long id){
        Optional<Transaction> found = transactionRepository.findById(id);
        found.orElseThrow(TransactionNotFoundException::new);
        found.get().setCustomer(null);
        transactionRepository.deleteTransactionById(id);
    }
    public List<Transaction> findByCustomerId(long id){
        return transactionRepository.findTransactionsByCustomerId(id);
    }
    public Transaction updateById(long id, Transaction t){
        Optional<Transaction> found = transactionRepository.findById(id);
        found.orElseThrow(TransactionNotFoundException::new);
        Transaction old = found.get();
        old.setValue(t.getValue());
        old.setCustomerId(t.getCustomerId());
        old.setTime(t.getTime());
        old.setPoints(t.getPoints());
        return transactionRepository.save(old);
    }
}
