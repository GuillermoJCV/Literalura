package services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"bookshelves", "copyright", "formats", "id", "media_type", "subjects", "translators"})
public class Book {
	
	public Book() {
		
	}
	
	public String getTitle() {
		return title;
	}
	public String getLanguages() {
		StringBuilder languages = new StringBuilder();
		
		for(String language : this.languages) {
			languages.append(language);
		}
		
		return languages.toString();
	}
	public Author getAuthor() {
		return authors[0];
	}
	
	public Author[] getAuthors() {
		return authors;
	}
	
	public int getDownloadCount() {
		return download_count;
	}
	
	@Override
	public String toString() {
		String result = "Title : " + getTitle() + "\n" +
						"Author : " + getAuthor().getName() + "\n" +
						"Languages : " + getLanguages() + "\n" +
						"Download Count : " + getDownloadCount();
		
		return result;
	}
	
	@JsonProperty("authors")
	private Author[] authors;

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("languages")
	private String[] languages;
	
	@JsonProperty("download_count")
	private int download_count;
	
}
