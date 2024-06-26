package alura.literalura.com.challenge.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "author", uniqueConstraints = @UniqueConstraint(columnNames = "author_id"))
public class AuthorModel {
	
	
	/* Todo esto sería generado por lombook */
	public AuthorModel(String author_name, Date birth_year, Date death_year) {
		super();
		this.author_name = author_name;
		this.birth_year = birth_year;
		this.death_year = death_year;
	}
	
	public AuthorModel() {
		
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private int author_id;

	@Column(name = "author_name")
	private String author_name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_year")
	private Date birth_year;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "death_year")
	private Date death_year;
	
	public int getAuthorId() {
		return author_id;
	}
	
	public String getAuthorName() {
		return author_name;
	}

	public void setAuthorName(String author_name) {
		this.author_name = author_name;
	}

	public Date getBirthYear() {
		return birth_year;
	}

	public void setBirthYear(Date birth_year) {
		this.birth_year = birth_year;
	}

	public Date getDeathYear() {
		return death_year;
	}

	public void setDeathYear(Date death_year) {
		this.death_year = death_year;
	}
}
