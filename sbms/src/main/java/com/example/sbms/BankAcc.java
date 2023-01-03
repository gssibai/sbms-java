package com.example.sbms;

import java.util.Random;

public class BankAcc extends PersonalData {
    public void setId(String id) {
        this.id = id;
    }

    private String id = null;
    private Double balance= 0.0;

    public String getId() {
        return id;
    }



    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void generateId() {
        Random iid = new Random();
        int low = 1000, high = 9999;
        Integer idNo = iid.nextInt(high - low) + low;
        id = idNo.toString();
    }

    public void deposit(double amount) {
        balance += amount;
        save();
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            save();
            return true;
        }
    }

    public boolean save() {
        if (this instanceof SecureAcc) {
            SecureAcc sa = (SecureAcc) this;
            return sa.save();
        } else {
            return false;
        }
    }
}
