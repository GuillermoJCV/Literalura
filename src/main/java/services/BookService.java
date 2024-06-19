package services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import models.BookModel;
import repositories.BooksRepository;

@Service
public class BookService implements BookServiceImpl {
	
	private final BooksRepository booksRepository;

	public BookService(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}
	
	@Override
	public BookModel save(BookModel book) {
		return booksRepository.save(book);
	}

	@Override
	public BookModel findBook(int id) {
		return booksRepository.findById(id).get();
	}

	@Override
	public BookModel update(int id, BookModel book) {
		BookModel updatedBook = booksRepository.findById(id).get();
		if(Objects.nonNull(book.getTitle()) && !book.getTitle().isBlank()) updatedBook.setTitle(book.getTitle());
		if(Objects.nonNull(book.getMediaType()) && !book.getMediaType().isBlank()) updatedBook.setMediaType(book.getMediaType());
		if(Objects.nonNull(book.isCopyright())) updatedBook.setCopyright(book.isCopyright());
		if(Objects.nonNull(book.getLanguages()) && book.getLanguages().length != 0) updatedBook.setTitle(book.getTitle());
		if(Objects.nonNull(book.getSubjects()) && book.getSubjects().length != 0) updatedBook.setSubjects(book.getSubjects());
		if(Objects.nonNull(book.getDownloadCount())) updatedBook.setDownloadCount(book.getDownloadCount());
		
		return booksRepository.save(updatedBook);
	}

	@Override
	public void delete(int id) {
		booksRepository.deleteById(id);
	}

}
