package alura.literalura.com.challenge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class BookModel {
	
	public BookModel() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaType() {
		return media_type;
	}

	public void setMediaType(String media_type) {
		this.media_type = media_type;
	}

	public boolean isCopyright() {
		return copyright;
	}

	public void setCopyright(boolean copyright) {
		this.copyright = copyright;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public String[] getSubjects() {
		return subjects;
	}

	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}

	public int getDownloadCount() {
		return download_count;
	}

	public void setDownloadCount(int download_count) {
		this.download_count = download_count;
	}

	public int getAuthorId() {
		return author_id;
	}

	public void setAuthorId(int author_id) {
		this.author_id = author_id;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@JoinColumn(name = "author_id")
	private int author_id;
}