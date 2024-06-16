package repositories;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import alura.literalura.com.challenge.AppConfig;
import models.AuthorModel;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
public class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	void saveMethod() {
		Calendar birthYear = new GregorianCalendar();
		Calendar deathYear = new GregorianCalendar();
		AuthorModel author = new AuthorModel();
		author.setAuthorName("Author name");
		author.setBirthYear(birthYear.getTime());
		author.setDeathYear(deathYear.getTime());
		authorRepository.save(author);
	}
	
}
