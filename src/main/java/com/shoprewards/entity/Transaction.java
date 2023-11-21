package com.shoprewards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="transactions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private int value;
    @NotNull
    private Timestamp time;
    private int points;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Customer.class)
    @JoinColumn(name="customer_id", insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;
    @Column(name = "customer_id")
    private long customerId;

    public Transaction(int value, Timestamp time, Customer customer, int points){
        this.value = value;
        this.time = time;
        this.customer = customer;
        this.points = points;
    }
    public Transaction(int value, Timestamp time, long customerId, int points){
        this.value = value;
        this.time = time;
        this.customerId = customerId;
        this.points = points;
    }
    @Override
    public String toString(){
        return "{value: " + this.value + ", time: " + this.time + ", customer_id: " + this.customerId + ", points: " + this.points + "}";
    }
}
