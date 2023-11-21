package com.shoprewards.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name="customers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotBlank
    @Size(max=200)
    private String name;
    @NotNull
    @NotBlank
    @Size(max=40)
    private String username;
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Transaction> transactions;
//    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
//    private Set<Transaction> transactions;
    public Customer(String name, String username){
        this.name = name;
        this.username = username;
    }

    public Customer(int i, String name, String username) {
        this.id = i;
        this.name = name;
        this.username = username;
    }
    public Customer(int i, String name, String username, Set<Transaction> t) {
        this.id = i;
        this.name = name;
        this.username = username;
    }

//    public Set<Transaction> getTransactions(){
//        return new HashSet<>();
//    }
//    public void setTransactions(Set<Transaction> t){
//
//    }

    @Override
    public String toString(){
        return "{name: " + this.name + ", username: " + this.username + "}";
    }
}
