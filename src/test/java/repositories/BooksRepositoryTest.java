package repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import alura.literalura.com.challenge.AppConfig;
import models.BookModel;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
public class BooksRepositoryTest {
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Test
	void saveMethod() {
		String[] languages = {"en", "fr", "es"};
		String [] subjects = {"something"};
		BookModel book = new BookModel();
		book.setAuthorId(0);
		book.setCopyright(false);
		book.setDownloadCount(0);
		book.setLanguages(languages);
		book.setSubjects(subjects);
		book.setMediaType(null);
		book.setTitle("Title book");
		
		booksRepository.save(book);
	}
	
}