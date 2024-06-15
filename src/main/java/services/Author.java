package services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

	public Author() {
		
	}
	
	public String getBirthYear() {
		return birthYear;
	}

	public String getDeathYear() {
		return deathYear;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		String result = "Name : " + this.getName() + "\n" +
						"Birthdate year : " + this.getBirthYear() + "\n" +
						"Death year : " + this.getDeathYear() + "\n";
		
		return result;
	}
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("birth_year")
	private String birthYear; 
	
	@JsonProperty("death_year")
	private String deathYear;
	
}
