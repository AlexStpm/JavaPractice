package org.alexstpmn;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {
    private final List<BankAccount> accountList = new ArrayList<>();

    public synchronized BankAccount createAccount(long balance) {
        BankAccount account = new BankAccount(balance);
        accountList.add(account);
        return account;
    }

    public boolean transfer(BankAccount from, BankAccount to, long amount) {
        if (amount <= 0 || from == null || to == null) {
            return false;
        }
        BankAccount first = from.getAccountUUID().compareTo(to.getAccountUUID()) < 0 ? from : to;
        BankAccount second = first == from ? to : from;
        synchronized (first) {
            synchronized (second) {
                if (!from.withdraw(amount)) {
                    return false;
                }
                if (!to.deposit(amount)) {
                    from.deposit(amount);
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized long  getTotalBalance() {
        long totalBalance = 0;
        for (BankAccount a : accountList) {
            totalBalance += a.getBalance();
        }
        return totalBalance;
    }
}
