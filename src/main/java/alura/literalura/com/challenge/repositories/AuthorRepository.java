package alura.literalura.com.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import alura.literalura.com.challenge.models.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, Integer> {
	
}