package alura.literalura.com.challenge;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.AuthorModel;
import models.BookModel;
import repositories.AuthorRepository;
import repositories.BooksRepository;
import services.Author;
import services.Book;
import services.Books;
import services.GutendexFetch;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	
	public static void main(String[] args) { SpringApplication.run(LiteraluraApplication.class, args); }
	
	@Override
	public void run(String... args) throws Exception {
		GutendexFetch fetch;
		MODES mode = searchBy();
		
		switch(mode) {
			/* POR SI SE QUIEREN AGREGAR MÁS METODOS DE BÚSQUEDA */
			case SEARCH_BOOK : {
				fetch = askForNameLoop("libro");
				isOneBook = false;
				break;
			}
			case SEARCH_AUTHOR : {
				fetch = askForNameLoop("autor");
				isSearchingAnAuthor = true;
				isOneBook = false;
				break;
			}
			default : {
				fetch = this.getRandomBook();
				isOneBook = true;
				break;
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		if(isOneBook) {
			System.out.println(fetch.getResponseJson());
		}
		else {
			Books books = mapper.readValue(fetch.getResponseJson(), Books.class);			
			try {
				if(books.getBooks()[0] != null) {
					for(Book book : books.getBooks()) {
						saveAuthors(book.getAuthors());
					}
					saveBooks(books.getBooks());
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Ningun registro fue encontrado con ese nombre");
			}

		}
		scan.close();
	}
	
	/* LE DA OPCIONES AL USUARIO PARA SABER QUÉ BUSCAR */
	public static MODES searchBy() {
		int mode;
		
		System.out.println("1 - Buscar nombre de libro");
		System.out.println("2 - Buscar nombre de autor");
		
		do {
			
			try {
				System.out.println("Elige un modo de busqueda válido");
				mode = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Eso no es un número");
				mode = -1;
			}
			
		}while(!(mode >= 1 && mode <= 2));
		
		switch(mode) {
			case 1: {
				return MODES.SEARCH_BOOK;
			}
			case 2 : {
				return MODES.SEARCH_AUTHOR;
			}
			default : {
				return MODES.SEARCH_AUTHOR;
			}
		}
		
	}
	
	/* Para guardar los autores e imprimirlos*/
	private void saveAuthors(Author[] authors) {
		for(Author author : authors) {
			if(!cache.contains(author.getName())) {
				if(isSearchingAnAuthor && author.getName().toLowerCase().contains(searchedName.toLowerCase())) System.out.println(author.toString());
				AuthorModel authorModel = new AuthorModel();
				authorModel.setAuthorName(author.getName());
				authorModel.setBirthYear(author.getBirthYear());
				authorModel.setDeathYear(author.getDeathYear());
				authorRepository.save(authorModel);
				authorRepository.flush();
				cache.add(author.getName());
			}
		}
	}
	
	/* Para guardar los libros e imprimirlos*/
	private void saveBooks(Book[] books) {
		boolean wasFinded = false;
		for(Book book : books) {
			if(!isSearchingAnAuthor && book.getTitle().toLowerCase().contains(searchedName.toLowerCase())) {
				wasFinded = true;
				System.out.println(book.toString());
			}
			BookModel bookModel = new BookModel();
			bookModel.setTitle(book.getTitle());
			bookModel.setSubjects(book.getSubjects());
			bookModel.setMediaType(book.getMediaType());
			bookModel.setLanguages(book.getLanguages());
			bookModel.setDownloadCount(book.getDownloadCount());
			bookModel.setCopyright(book.isCopyright());
			booksRepository.save(bookModel);
		}
		if(!isSearchingAnAuthor && !wasFinded) System.out.println("No hay ningun libro que contenga esa secuencia de letras");
	}
	
	private GutendexFetch getRandomBook() throws URISyntaxException, IOException, InterruptedException {
		System.out.println("El modo sugerido no fue encontrado, así que te sugerimos este libro");
		Random random = new Random();
		GutendexFetch fetch = new GutendexFetch(random.nextInt());
		return fetch;
	}
	
	/* CREA UN LOOP HASTA QUE EL USUARIO INSERTE UN VALOR VALIDO */
	private GutendexFetch askForNameLoop(String searching) throws URISyntaxException, IOException, InterruptedException {
		System.out.println("Ingresa el nombre del " + searching + " que deseas buscar");
		searchedName = scan.nextLine();
		GutendexFetch fetch = new GutendexFetch(GutendexFetch.SEARCH_BY.NAME, searchedName);
		
		while(fetch.getStatusCode() >= 300) {
			System.out.println("El título buscado no ha sido encontrado, intenta otro nombre");
			searchedName = scan.nextLine();
			fetch = new GutendexFetch(GutendexFetch.SEARCH_BY.NAME, searchedName);
		}
		
		return fetch;
	}
	
	private enum MODES {
		SEARCH_BOOK,
		SEARCH_AUTHOR
		
	}
	
	private String searchedName;
	private static Set<String> cache = new HashSet<>();
	private static Scanner scan = new Scanner(System.in);
	private static boolean isSearchingAnAuthor = false;
	private static boolean isOneBook;
	
	/* REPOSITORIES */
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
}