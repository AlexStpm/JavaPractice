package org.alexstpmn;

import java.util.UUID;

public class BankAccount {
    private final UUID accountUUID;
    private long balance;

    public BankAccount(long balance) {
        accountUUID = UUID.randomUUID();
        if (balance < 0) {
            throw new IllegalArgumentException();
        }
        this.balance = balance;
    }

    public UUID getAccountUUID() {
        return accountUUID;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public synchronized boolean deposit(long amount) {
        if (amount <= 0 || amount + balance < 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public synchronized boolean withdraw(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
