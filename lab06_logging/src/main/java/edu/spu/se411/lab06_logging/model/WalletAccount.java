package edu.spu.se411.lab06_logging.model;

import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletAccount {
	private static final Logger logger = LoggerFactory.getLogger(WalletAccount.class);

	private double balance;

    public WalletAccount(double balance) {
        setBalance(balance);
        logger.debug("Wallet account created with balance {}", balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        logger.debug("Withdrawal requested: amount={}, balance={}", amount, balance);
        if(amount < 0) {
			throw new IllegalArgumentException("Cannot withdraw negative number: " + amount);
		} else if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Your balance is " + balance);
        } else {
            balance -= amount;
            logger.debug("Withdrawal successful: amount={}, remaining balance={}", amount, balance);
        }
    }
    
    public void deposit(double amount) throws IllegalArgumentException {
        logger.debug("Deposit requested: amount={}, balance={}", amount, balance);
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit negative number: " + amount);
        } else {
            balance += amount;
            logger.debug("Deposit successful: amount={}, new balance={}", amount, balance);
        }
    }

    public void setBalance(double balance) {
    	if (balance < 0) {
			throw new IllegalArgumentException("Balance cannot be negative: " + balance);
		}
    	
		this.balance = balance;
	}
	
}
