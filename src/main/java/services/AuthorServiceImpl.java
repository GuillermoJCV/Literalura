package services;

import models.AuthorModel;

public interface AuthorServiceImpl {
	AuthorModel save(AuthorModel author);				/* CREATE */
	AuthorModel findAuthor(int id); 					/* READ */
	AuthorModel update(int id, AuthorModel author);		/* UPDATE */
	void delete(int id);								/* DELETE */
}