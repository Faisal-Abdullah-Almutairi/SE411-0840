package edu.psu.se411.lab09_inventory.model;

public class SearchByAuthor implements SearchStrategy<Item> {

	private final String authorName;

	public SearchByAuthor(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public boolean matches(Item item) {
		return item instanceof Book book && book.getAuthorName().equalsIgnoreCase(authorName);
	}
}
