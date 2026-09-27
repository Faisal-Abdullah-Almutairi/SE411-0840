package edu.spu.se411.lab06_logging;

import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;
import edu.spu.se411.lab06_logging.model.WalletAccount;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	static {
		try {
			Files.createDirectories(Paths.get("logs", "App", "log4j"));
		} catch (IOException e) {
			throw new UncheckedIOException("Could not create logging directory", e);
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");
		try {
			WalletAccount account = new WalletAccount(1000);
			try {
				account.withdraw(1500);
			} catch (InsufficientFundsException e) {
				logger.error("Withdrawal failed", e);
			}

			try {
				account.deposit(-100);
			} catch (IllegalArgumentException e) {
				logger.error("Deposit failed", e);
			}
		} finally {
			logger.info("Application is ending...");
		}
	}
}
