package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.Objects;

public class Wallet {
    private BigDecimal balance;

    public Wallet(BigDecimal initialBalance) {
        Objects.requireNonNull(initialBalance, "Initial balance cannot be null.");
        if (initialBalance.signum() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdrawToBank(BigDecimal amount, BankAccount bankAccount)
            throws InsufficientFundsException {
        Objects.requireNonNull(amount, "Withdrawal amount cannot be null.");
        Objects.requireNonNull(bankAccount, "Bank account cannot be null.");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException("Insufficient funds in wallet.");
        }

        bankAccount.deposit(amount);
        balance = balance.subtract(amount);
    }
}
