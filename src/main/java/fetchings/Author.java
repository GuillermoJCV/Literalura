package fetchings;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

	public Author() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public Date getBirthYear() {
		Calendar birthYear = new GregorianCalendar();
		birthYear.add(Calendar.DAY_OF_MONTH, 0);
		birthYear.add(Calendar.MONTH, 0);
		birthYear.add(Calendar.YEAR, this.birthYear);
		return birthYear.getTime();
	}

	public Date getDeathYear() {
		Calendar deathYear = new GregorianCalendar();
		deathYear.add(Calendar.DAY_OF_MONTH, 0);
		deathYear.add(Calendar.MONTH, 0);
		deathYear.add(Calendar.YEAR, this.deathYear);
		return deathYear.getTime();
	}
	
	@Override
	public String toString() {
		String result = "Name : " + this.getName() + "\n" +
						"Birthdate year : " + this.getBirthYear() + "\n" +
						"Death year : " + this.getDeathYear() + "\n" +
						"<-------------------------------->";
		
		return result;
	}

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("birth_year")
	private int birthYear; 
	
	@JsonProperty("death_year")
	private int deathYear;
	
}
