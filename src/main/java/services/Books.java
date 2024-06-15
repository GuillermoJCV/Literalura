package services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties({"next", "previous"})
public class Books {
	
	public int getCount() {
		return count;
	}
	
	public Book[] getBooks() {
		return books;
	}
	
	public String getNextPage() {
		return nextPage;
	}

	public String getPreviousPage() {
		return previousPage;
	}
	
	@JsonProperty("count")
	private int count;
	
	@JsonSetter("next")
	private String nextPage;
	
	@JsonSetter("previous")
	private String previousPage;
	
	@JsonSetter("results")
	private Book[] books;

	public Books() {
		
	}
}
