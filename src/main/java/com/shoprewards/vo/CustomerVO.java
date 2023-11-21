package com.shoprewards.vo;

import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerVO {
    private long id;
    private String username;
    private String  name;
    private List<Transaction> transactions;
    private int totalRewards;
    private int[] monthlyRewards;
    public CustomerVO(Customer c, List<Transaction> transactions, int totalRewards, int[] monthlyRewards){
        this.id = c.getId();
        this.username = c.getUsername();
        this.name = c.getName();
        this.transactions = transactions;
        this.totalRewards = totalRewards;
        this.monthlyRewards = monthlyRewards;
    }

}
