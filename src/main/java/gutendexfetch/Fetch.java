package gutendexfetch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Fetch {
	
	public Fetch() {
		
	}
	
	public String getBookByName(String name) throws URISyntaxException, IOException, InterruptedException {
		
		HttpResponse<String> response = getResponse("search=", name);
		return response.body();
	}
	
	private HttpResponse<String> getResponse(String resource, String itemToAccess) throws URISyntaxException, IOException, InterruptedException {
		
		HttpRequest request;
		request = useFetch(resource, itemToAccess);
		response = saveResponse(request);
		return response;
	}
	
	private HttpResponse<String> saveResponse(HttpRequest request) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client
				  .send(request, HttpResponse.BodyHandlers.ofString());
		
		return response;
	}
	
	private HttpRequest useFetch(String url, String itemToAcess) throws URISyntaxException, UnsupportedEncodingException, MalformedURLException{
		
		HttpRequest request = HttpRequest.newBuilder()
				  .uri(useURL(url + itemToAcess))
				  .GET()
				  .build();
		
		return request;
	}
	
	private URI useURL(String item) throws URISyntaxException, MalformedURLException {
		URI result;
		
		final String scheme = "https";
		final String host = TARGET_URL;
		final String path = REST_RESOURCE;
		final String query = item;
		
		//TODO: Contemplar cuando la query está vacía
		
		result = new URI(scheme, host, path, query, null);
		System.out.println(result.toURL());
		
		return result;
	}
	
	private final String TARGET_URL = "gutendex.com";
	private final String REST_RESOURCE = "/books";
	private HttpResponse<String> response;
}
