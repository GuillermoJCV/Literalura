package alura.literalura.com.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import alura.literalura.com.challenge.models.BookModel;

public interface BooksRepository extends JpaRepository<BookModel, Integer> {

}