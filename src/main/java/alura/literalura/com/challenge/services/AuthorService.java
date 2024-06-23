package alura.literalura.com.challenge.services;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.literalura.com.challenge.models.AuthorModel;
import alura.literalura.com.challenge.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	public void save(AuthorModel author) {
		boolean existsAuthor = repo.findById(author.getAuthorId()).isEmpty();
		
		if(!existsAuthor) repo.save(author);
	}
	
	public void saveAll(Iterator<AuthorModel> authors) {
		while(authors.hasNext()) {
			AuthorModel author = authors.next();
			boolean existsAuthor = repo.findById(author.getAuthorId()).isEmpty();
			if(!existsAuthor) repo.save(author);
		}
	}
	
	public AuthorModel findByName() throws Exception {
		// TODO : Implementar el método findByName
		throw new Exception("Not implemented method");
	}
	
	public AuthorService(AuthorRepository repo) {
		super();
		this.repo = repo;
	}

	private final AuthorRepository repo;
}
