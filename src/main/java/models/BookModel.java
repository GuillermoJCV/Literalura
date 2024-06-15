package models;

import jakarta.persistence.*;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class BookModel {
	
	public BookModel(String title, String media_type, boolean copyright, String[] languages, String[] subjects,
			int download_count, int author_id) {
		super();
		this.title = title;
		this.media_type = media_type;
		this.copyright = copyright;
		this.languages = languages;
		this.subjects = subjects;
		this.download_count = download_count;
		this.author_id = author_id;
	}

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "media_type", nullable = true)
	private String media_type;
	
	@Column(name = "copyright", nullable = true)
	private boolean copyright;
	
	@Column(name = "languages", nullable = true)
	private String[] languages;
	
	@Column(name = "subjects", nullable = true)
	private String[] subjects;

	@Column(name = "download_count", columnDefinition = "integer default 0")
	private int download_count;
	
	@ManyToMany
	@Column(name = "author_id") 
	private int author_id;
	
	
}