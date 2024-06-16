package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, Integer> {

}
