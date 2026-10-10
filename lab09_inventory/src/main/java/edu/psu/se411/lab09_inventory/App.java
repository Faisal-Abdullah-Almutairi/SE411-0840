package edu.psu.se411.lab09_inventory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab09_inventory.model.Book;
import edu.psu.se411.lab09_inventory.model.ElectronicDevice;
import edu.psu.se411.lab09_inventory.model.Inventory;
import edu.psu.se411.lab09_inventory.model.Item;
import edu.psu.se411.lab09_inventory.model.SearchByAuthor;
import edu.psu.se411.lab09_inventory.model.SearchByCategory;
import edu.psu.se411.lab09_inventory.model.SearchById;
import edu.psu.se411.lab09_inventory.model.SearchByName;
import edu.psu.se411.lab09_inventory.model.SearchStrategy;

public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");

		try {
			Inventory<Item> inventory = new Inventory<>();
			inventory.addItem(new Book(1, "Concurrency in Java", "Brian Goetz"));
			inventory.addItem(new Book(2, "Effective Java", "Joshua Bloch"));
			inventory.addItem(new ElectronicDevice(3, "Galaxy S26 Ultra", "Smartphone"));
			inventory.addItem(new ElectronicDevice(4, "ThinkPad X1", "Laptop"));

			displayInventory(inventory);

			printResults("Search by name", inventory.findItems(new SearchByName("Concurrency in Java")));
			printResults("Search by device name", inventory.findItems(new SearchByName("ThinkPad X1")));
			printResults("Search by id", inventory.findItems(new SearchById(3)));
			printResults("Search by author", inventory.findItems(new SearchByAuthor("Joshua Bloch")));
			printResults("Search by category", inventory.findItems(new SearchByCategory("Smartphone")));

			SearchStrategy<Item> nameContainsJava = item -> item.getName().contains("Java");
			printResults("Lambda search", inventory.findItems(nameContainsJava));
		} catch (Exception exception) {
			logger.error("An error occurred while managing the inventory.", exception);
		} finally {
			logger.info("Application is closing...");
		}
	}

	public static void displayInventory(Inventory<?> inventory) {
		System.out.println("Inventory contents:");
		for (Object item : inventory.getAllItems()) {
			System.out.println(item);
		}
	}

	private static void printResults(String title, List<?> results) {
		System.out.println("\n" + title + ":");
		for (Object result : results) {
			System.out.println(result);
		}
	}
}
