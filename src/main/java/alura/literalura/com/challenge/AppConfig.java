package alura.literalura.com.challenge;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import repositories.AuthorRepository;
import repositories.BooksRepository;
import services.AuthorService;
import services.BookService;

@Configuration
public class AppConfig {
	
	@Bean
	BookService bookService(BooksRepository booksRepository) {
		return new BookService(booksRepository);
	}
	
	@Bean
	AuthorService authorService(AuthorRepository authorRepository) {
		return new AuthorService(authorRepository);
	}
}