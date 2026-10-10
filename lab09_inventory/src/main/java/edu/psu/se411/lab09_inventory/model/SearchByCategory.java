package edu.psu.se411.lab09_inventory.model;

public class SearchByCategory implements SearchStrategy<Item> {

	private final String category;

	public SearchByCategory(String category) {
		this.category = category;
	}

	@Override
	public boolean matches(Item item) {
		return item instanceof ElectronicDevice device && device.getCategory().equalsIgnoreCase(category);
	}
}
