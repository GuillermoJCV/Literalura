package alura.literalura.com.challenge.fetchings;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

public class GutendexFetch {
	
	/* CONSTRUCTORS */
	
	public GutendexFetch(int id) throws URISyntaxException, IOException, InterruptedException {
		URI uri = createURIByBookId(id);
		
		HttpRequest request = createRequest(uri);
		HttpClient client = createClient();
		requestResponse = createResponse(client, request);
	}
	
	public GutendexFetch(SEARCH_BY type, String value) throws URISyntaxException, IOException, InterruptedException {
		URI uri = null;
		
		switch(type) {
			case NAME : {
				uri = createURIByBookName(value);
				break;
			}
			case TOPIC : {
				uri = createURIByBookTopic(value);
				break;
			}
		}
		
		HttpRequest request = createRequest(uri);
		HttpClient client = createClient();
		requestResponse = createResponse(client, request);
	}
	
	public GutendexFetch(SEARCH_BY_ARRAY_OF type, String[] values) throws URISyntaxException, IOException, InterruptedException {
		URI uri = null;
		StringBuilder builder = new StringBuilder();
		for(String value : values) {
			builder.append(value);
			builder.append(',');
		}
		builder.deleteCharAt(builder.length() - 1);
		String valuesStr = builder.toString();
		
		switch(type) {
			case IDS : {
				uri = createURIByBookIds(valuesStr);
				break;
			}
			case LANGUAGES : {
				uri = createURIByBookLanguages(valuesStr);
				break;
			}
		}
		
		HttpRequest request = createRequest(uri);
		HttpClient client = createClient();
		requestResponse = createResponse(client, request);
		
	}
	
	/* GETTERS */
	
	public String getURI() {
		return requestResponse.uri().toString();
	}
	
	public int getStatusCode() {
		return requestResponse.statusCode();
	}
	
	public String getResponseJson() {
		
		if(requestResponse.statusCode() == 200) {
			return requestResponse.body();
		} else {
			return String.valueOf(requestResponse.statusCode());
		}
	}
	
	/* HTTP METHODS */
	
	/* BY NAME */
	private URI createURIByBookName(String name) throws URISyntaxException {
		return new URI("https", "gutendex.com", "/books/", "search=" + name, null);
	}
	
	/* BY ID */
	private URI createURIByBookId(int id) throws URISyntaxException {
		return new URI("https", "gutendex.com", "/books/" + id + "/", null);
	}
	
	/* BY IDS */
	private URI createURIByBookIds(String values) throws URISyntaxException {
		return new URI("https", "gutendex.com", "/books/", "ids=" + values, null);
	}
	
	/* BY LANGUAGES */
	private URI createURIByBookLanguages(String languages) throws URISyntaxException {
		return new URI("https", "gutendex.com", "/books/", "languages=" + languages, null);
	}
	
	/* BY TOPIC */
	private URI createURIByBookTopic(String topic) throws URISyntaxException {
		return new URI("https", "gutendex.com", "/books/", "topic=" + topic, null);
	}
	
	private HttpRequest createRequest(URI uri) {
		System.out.println("Buscando...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        
        return request;
	}
	
	private HttpClient createClient() {
		HttpClient client = HttpClient.newHttpClient();
		return client;
	}
	
	private HttpResponse<String> createResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
		HttpResponse<String> response=client.send(request, BodyHandlers.ofString());
		return response;
	}
	
	/* VARIABLES & CONSTANTS */
	
	private HttpResponse<String> requestResponse;
	
	public static enum SEARCH_BY {
		NAME,
		TOPIC
	}
	public static enum SEARCH_BY_ARRAY_OF {
		IDS,
		LANGUAGES
	}
}
