package alura.literalura.com.challenge;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gutendexfetch.Fetch;

@SpringBootApplication
public class LiteraluraApplication {
	
	public static void main(String[] args) {
		
		Fetch books = new Fetch();
		
		SpringApplication.run(LiteraluraApplication.class, args);
		
		try {
			String response = books.getBookByName("dickens great");
			System.out.println("Response: ");
			System.out.println(response);
		} catch (URISyntaxException | IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
