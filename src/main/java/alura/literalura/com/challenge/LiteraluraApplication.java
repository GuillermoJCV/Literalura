package alura.literalura.com.challenge;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gutendexfetch.Fetch;

@SpringBootApplication
public class LiteraluraApplication {
	
	public static void main(String[] args) {
		Fetch fetch = new Fetch();
		
		SpringApplication.run(LiteraluraApplication.class, args);
		
		try {
			HttpResponse<String> response = fetch.getResponse("/books");
			System.out.println("Response: ");
			System.out.println(response.body());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			System.err.println(e);
		}
	}

}
