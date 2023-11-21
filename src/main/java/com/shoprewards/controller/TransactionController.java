package com.shoprewards.controller;

import com.shoprewards.entity.Transaction;
import com.shoprewards.exception.TransactionNotFoundException;
import com.shoprewards.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@CrossOrigin()
public class TransactionController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService ts){
        this.transactionService = ts;
    }
    //param t takes customer_id
    @PostMapping("/")
    @ResponseBody
    public Transaction postTransaction(@RequestBody Transaction t) throws SQLIntegrityConstraintViolationException {
        return transactionService.save(t);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Transaction getTransactionById(@PathVariable int id) {
        Optional<Transaction> result = transactionService.findById(id);
        result.orElseThrow(TransactionNotFoundException::new);
        return result.get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {
        transactionService.deleteById(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Transaction updateTransaction(@PathVariable int id, @RequestBody Transaction t) {
        return transactionService.updateById(id, t);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sqlIntegrityConstraintViolationExceptionHandler(){
        return new ResponseEntity<String>("Customer for this transaction doesn't exists.", HttpStatus.BAD_REQUEST);
    }

}
