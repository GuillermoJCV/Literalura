package alura.literalura.com.challenge;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;

//import java.io.IOException;
//import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		String name = "";
		MODES mode = searchBy();
		
		
		switch(mode) {
			case SEARCH_BOOK : {
				System.out.println("Ingresa el titulo de libro que deseas buscar");
				name = scan.nextLine();
				
				fetch = new GutendexFetch(GutendexFetch.SEARCH_BY.NAME, name);
				
				fetch = askForNameLoop(name, fetch);
				isOneBook = false;
				break;
			}
			case SEARCH_AUTHOR : {
				System.out.println("Ingresa el nombre del autor que deseas buscar");
				name = scan.nextLine();
				
				fetch = new GutendexFetch(GutendexFetch.SEARCH_BY.NAME, name);
				
				fetch = askForNameLoop(name, fetch);
				isOneBook = false;
				break;
			}
			default : {
				System.out.println("El modo sugerido no fue encontrado, así que te sugerimos este libro");
				Random random = new Random();
				fetch = new GutendexFetch(random.nextInt());
				isOneBook = false;
				break;
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		if(isOneBook) {
			System.out.println(fetch.getResponseJson());
		}
		else {
			Books books = mapper.readValue(fetch.getResponseJson(), Books.class);
			
			System.out.println(fetch.getResponseJson());
			
			if(books.getBooks() != null) {
				for(Book book : books.getBooks()) {
					
					if(isSearchingAnAuthor) {
						Author[] authors = book.getAuthors();
						
						for(Author author : authors) {
							if(!cache.contains(author.getName())) {
								cache.add(author.getName());
								System.out.println(author.toString());
								System.out.println("<------------------------------>");
							}
						}
					} else {
						System.out.println(book.toString());
						System.out.println("<------------------------------>");
					}
				}
			} else {
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
			default : {
				isSearchingAnAuthor = true;
				return MODES.SEARCH_AUTHOR;
			}
		}
		
	}
	
	/* CREA UN LOOP HASTA QUE EL USUARIO INSERTE UN VALOR VALIDO */
	private GutendexFetch askForNameLoop(String name, GutendexFetch InitialFetch) throws URISyntaxException, IOException, InterruptedException {
		GutendexFetch fetch = InitialFetch;
		while(fetch.getStatusCode() >= 300) {
			System.out.println("El título buscado no ha sido encontrado, intenta otro nombre");
			name = scan.nextLine();
			fetch = new GutendexFetch(GutendexFetch.SEARCH_BY.NAME, name);
		}
		
		return fetch;
	}
	
	private enum MODES {
		SEARCH_BOOK,
		SEARCH_AUTHOR
		
	}
	
	private static Set<String> cache = new HashSet<>();
	private static Scanner scan = new Scanner(System.in);
	private static boolean isSearchingAnAuthor = false;
	private static boolean isOneBook;

}