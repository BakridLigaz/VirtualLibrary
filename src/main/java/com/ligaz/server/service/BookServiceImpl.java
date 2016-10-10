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
        return repository.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return repository.findBooksByGenre(genre);
    }


    @Override
    public List<Book> getBooksBySearch(String searchQuery) {
        return repository.findBooksBySearchQuery(searchQuery);
    }

    @Override
    public Book getBookByName(String name) {
        return repository.findBooksByName(name);
    }

    @Override
    public Book getBook(long id) {
        long currentCount = repository.findOne(id).getCountViews();
        currentCount++;
        Book tempBook = repository.findOne(id);
        tempBook.setCountViews(currentCount);
        repository.saveAndFlush(tempBook);
        return repository.findOne(id);
    }

    @Override
    public String updateBook(Book book) {
            repository.saveAndFlush(book);
        return "Книга " + book.getName() + " измена";
    }

    @Override
    public String deleteBook(long id) {
        Book b = repository.getOne(id);
        if (repository.exists(id)) {
            repository.delete(id);
            return "Книга " + b.getName() + " удалена";
        }
        return "Книга " + b.getName() + " не найдена";
    }

    @Override
    public String addBook(Book book) {
        if(!repository.exists(book.getId())){
            repository.saveAndFlush(book);
            return "Книга " + book.getName() + " добавлена";
        }
        return "Книга с id " + book.getId() + " уже существует";
    }
}
