package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;
import edu.psu.se411.exceptions.InvalidAgeException;
import java.math.BigDecimal;

public class App {
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18.");
        }
        System.out.println("Age valid message.");
    }

    public static void main(String[] args) {
        try {
            validateAge(20);
            validateAge(16);
        } catch (InvalidAgeException exception) {
            System.out.println(exception.getMessage());
        }

        Wallet wallet = new Wallet(new BigDecimal("100.00"));
        BankAccount bankAccount = new BankAccount(new BigDecimal("50.00"));

        try {
            wallet.withdrawToBank(new BigDecimal("40.00"), bankAccount);
            System.out.println("Withdrawal successful. Wallet: " + wallet.getBalance()
                    + ", bank account: " + bankAccount.getBalance());

            wallet.withdrawToBank(new BigDecimal("100.00"), bankAccount);
        } catch (InsufficientFundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
