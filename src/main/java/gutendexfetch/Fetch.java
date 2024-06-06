package gutendexfetch;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Fetch {
	
	private final String targetURL = "https://gutendex.com";
	private HttpResponse<String> response;
	
	public Fetch() {
		
	}
	
	private HttpRequest useFetch(String url) throws URISyntaxException{
		String completeURL = targetURL + url;
		HttpRequest request = HttpRequest.newBuilder()
				  .uri(new URI(completeURL))
				  .GET()
				  .build();
		
		return request;
	}
	
	public HttpResponse<String> saveResponse(HttpRequest request) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client
				  .send(request, HttpResponse.BodyHandlers.ofString());
		
		return response;
	}
	
	public HttpResponse<String> getResponse(String resource) throws URISyntaxException, IOException, InterruptedException {
		HttpRequest request;
		request = useFetch(resource);
		response = saveResponse(request);
		return response;
	}
}
