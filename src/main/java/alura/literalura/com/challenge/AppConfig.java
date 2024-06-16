package alura.literalura.com.challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import models.AuthorModel;
import models.BookModel;
import repositories.AuthorRepository;
import repositories.BooksRepository;

@Configuration
public class AppConfig {

	@Bean
	BooksRepository booksRepository() {
		return new BookModel();
	}
	
	@Bean
	AuthorRepository authorRepository() {
		return new AuthorModel();
	}
	
}