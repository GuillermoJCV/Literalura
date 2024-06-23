package alura.literalura.com.challenge.fetchings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"bookshelves", "formats", "id", "translators"})
public class Book {
	
	public Book() {
		
	}
	
	public String getTitle() {
		return title;
	}
	public String[] getLanguages() {
		return languages;
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
	
	public String[] getSubjects() {
		return subjects;
	}
	
	public String getMediaType() {
		return mediaType;
	}
	
	public boolean isCopyright() {
		return copyright;
	}
	
	@Override
	public String toString() {
		String result = "Title : " + getTitle() + "\n" +
						"Author : " + getAuthor().getName() + "\n" +
						"Languages : " + getLanguages()[0] + "\n" +
						"Download Count : " + getDownloadCount() + "\n" +
						"<-------------------------------->";
		
		return result;
	}
	
	@JsonProperty("copyright")
	private boolean copyright;

	@JsonProperty("media_type")
	private String mediaType;

	@JsonProperty("subjects")
	private String[] subjects;

	@JsonProperty("authors")
	private Author[] authors;

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("languages")
	private String[] languages;
	
	@JsonProperty("download_count")
	private int download_count;
	
}
