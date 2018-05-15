package net.chemodurov.tutorial.apachepoispring.dao;

import net.chemodurov.tutorial.apachepoispring.model.entities.Book;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateBookDAOImpl implements BookDAO {
    @Override
    public void add(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.save(book);
        session.close();
    }

    @Override
    public Book getById(long id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;    }

    @Override
    public void update(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(book);
        session.flush();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.delete(book);
        session.flush();
        session.close();
    }

    @Override
    public List<Book> getAll() {
        List<Book> books;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        books = new ArrayList<Book>(session.createQuery("FROM Book").list());
        session.close();
        return books;
    }
}
