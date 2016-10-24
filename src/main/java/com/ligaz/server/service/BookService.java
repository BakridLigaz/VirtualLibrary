package com.ligaz.server.service;

import com.ligaz.server.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks(int offset, int limit);

    long getAllListLenght();

    List<Book> getBooksByAuthor(int offset, int limit, int authorId);

    List<Book> getBooksByGenre(int offset, int limit, int genreId);

    List<Book> getBooksBySearch(int offset, int limit, String searchQuery);

    Book getBookByName(String name);

    Book getBook(long id);

    String updateBook(Book book);

    boolean deleteBook(long id);

    String addBook(Book book);

    boolean isUniqueName(String name);

    byte[] getImage(Long id);

    boolean addContent(Book book, byte[] content);

    long getListLenghtByAuthor(int authorId);

    long getListLenghtByGenre(int genreId);

    long getListLengthBySearch(String query);

    List<Book> getBooksByRandom(int limit);

    byte[] getContent(String bookName);
}
