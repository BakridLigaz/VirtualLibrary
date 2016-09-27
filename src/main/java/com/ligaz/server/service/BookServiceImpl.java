package com.ligaz.server.service;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Genre;
import com.ligaz.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository repository;
    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return repository.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return null;
    }


    @Override
    public List<Book> getBooksBySearch() {
        return null;
    }

    @Override
    public Book getBook(long id) {
        return repository.findOne(id);
    }

    @Override
    public void editBook(Book book) {
        if(repository.exists(book.getId())){
            repository.saveAndFlush(book);
        }
    }

    @Override
    public void deleteBook(Book book) {
        if(repository.exists(book.getId())){
            repository.delete(book);
        }
    }

    @Override
    public void addBook(Book book) {
        if(!repository.exists(book.getId())){
            repository.saveAndFlush(book);
        }
    }
}
