package models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "author", uniqueConstraints = @UniqueConstraint(columnNames = "author_id"))
public class AuthorModel {
	
	public AuthorModel(String author_name, Date birth_year, Date death_year) {
		super();
		this.author_name = author_name;
		this.birth_year = birth_year;
		this.death_year = death_year;
	}

	@Id @GeneratedValue
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
}
