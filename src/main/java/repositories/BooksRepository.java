package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.BookModel;

public interface BooksRepository extends JpaRepository<BookModel, Integer> {

}