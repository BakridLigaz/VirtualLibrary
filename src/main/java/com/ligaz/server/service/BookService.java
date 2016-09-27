package com.ligaz.server.service;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Genre;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public List<Book> getBooksByAuthor(Author author);
    public List<Book> getBooksByGenre(Genre genre);
    public List<Book> getBooksBySearch();
    public Book getBook(long id);
    public void editBook(Book book);
    public void deleteBook(Book book);
    public void addBook(Book book);
}
