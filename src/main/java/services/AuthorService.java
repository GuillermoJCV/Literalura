package services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import models.AuthorModel;
import repositories.AuthorRepository;

@Service
public class AuthorService implements AuthorServiceImpl {
	
	private final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public AuthorModel save(AuthorModel author) {
		return authorRepository.save(author);
	}

	@Override
	public AuthorModel findAuthor(int id) {
		return authorRepository.findById(id).get();
	}

	@Override
	public AuthorModel update(int id, AuthorModel author) {
		AuthorModel updatedAuthor = authorRepository.findById(id).get();
		if(Objects.nonNull(author.getAuthorName()) && !author.getAuthorName().isBlank()) updatedAuthor.setAuthorName(author.getAuthorName());
		if(Objects.nonNull(author.getBirthYear())) updatedAuthor.setBirthYear(author.getBirthYear());
		if(Objects.nonNull(author.getDeathYear())) updatedAuthor.setDeathYear(author.getDeathYear());
		
		return authorRepository.save(updatedAuthor);
	}

	public void delete(int id) {
		authorRepository.deleteById(id);
	}

}