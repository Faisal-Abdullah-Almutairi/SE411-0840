package edu.psu.se411;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
    private BigDecimal balance;

    public BankAccount(BigDecimal initialBalance) {
        Objects.requireNonNull(initialBalance, "Initial balance cannot be null.");
        if (initialBalance.signum() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        Objects.requireNonNull(amount, "Deposit amount cannot be null.");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance = balance.add(amount);
    }
}
