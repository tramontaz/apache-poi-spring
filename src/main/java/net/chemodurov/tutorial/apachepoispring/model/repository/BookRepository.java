package net.chemodurov.tutorial.apachepoispring.model.repository;

import net.chemodurov.tutorial.apachepoispring.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query(value = "SELECT * FROM books;", nativeQuery = true)
    List<Book> findAll();
//    Book findById();
}