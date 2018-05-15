package net.chemodurov.tutorial.apachepoispring.dao;

import net.chemodurov.tutorial.apachepoispring.model.entities.Book;

import java.util.List;

//@Component
public interface BookDAO {
    void add(Book book);
    Book getById(long id);
    void update(Book book);
    void delete(long id);
    List<Book> getAll();
}
