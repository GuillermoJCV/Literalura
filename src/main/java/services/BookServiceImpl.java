package services;

import models.BookModel;

public interface BookServiceImpl {
	BookModel save(BookModel book); 			/* CREATE */
	BookModel findBook(int id);					/* READ */
	BookModel update(int id, BookModel book);	/* UPDATE */
	void delete(int id);						/* DELETE */
}