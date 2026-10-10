package edu.psu.se411.lab09_inventory.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory<T> {

	private final List<T> items = new ArrayList<>();

	public void addItem(T item) {
		items.add(item);
	}

	public boolean removeItem(T item) {
		return items.remove(item);
	}

	public List<T> getAllItems() {
		return Collections.unmodifiableList(items);
	}

	public List<T> findItems(SearchStrategy<T> strategy) {
		List<T> matchingItems = new ArrayList<>();
		for (T item : items) {
			if (strategy.matches(item)) {
				matchingItems.add(item);
			}
		}
		return matchingItems;
	}
}
