package com.ligaz.server.service;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Genre;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public List<Book> getBooksByAuthor(Author author);
    public List<Book> getBooksByGenre(Genre genre);

    public List<Book> getBooksBySearch(String searchQuery);

    public Book getBookByName(String name);

    public Book getBook(long id);

    public String updateBook(Book book);

    public String deleteBook(long id);

    public String addBook(Book book);
}
