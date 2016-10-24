package com.ligaz.server.service;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Content;
import com.ligaz.server.entity.Genre;
import com.ligaz.server.repository.AuthorRepository;
import com.ligaz.server.repository.BookRepository;
import com.ligaz.server.repository.ContentRepository;
import com.ligaz.server.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository repository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Book> getAllBooks(int offset,int limit) {
        Page<Book> books = repository.findAll(new PageRequest(offset,limit, Sort.Direction.ASC,"name"));
        return  books.getContent();
    }

    @Override
    public long getAllListLenght() {
        return repository.count();
    }

    @Override
    public List<Book> getBooksByAuthor(int offset, int limit, int authorId) {
        Author author = authorRepository.findOne(authorId);
        return repository.findBooksByAuthor(author,new PageRequest(offset,limit, Sort.Direction.ASC,"name"));
    }

    @Override
    public List<Book> getBooksByGenre(int offset, int limit, int genreId) {
        Genre genre = genreRepository.findOne(genreId);
        return repository.findBooksByGenre(genre,new PageRequest(offset,limit, Sort.Direction.ASC,"name"));
    }


    @Override
    public List<Book> getBooksBySearch(int offset, int limit, String searchQuery) {
        String query = "%"+searchQuery+"%";
        return repository.findBooksBySearchQuery(query, new PageRequest(offset,limit, Sort.Direction.ASC,"name"));
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
    public boolean deleteBook(long id) {
        Book book = repository.findOne(id);
        if (repository.exists(id)) {
            contentRepository.delete(id);
            repository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public String addBook(Book book) {
        if(!repository.exists(book.getId())){
            repository.saveAndFlush(book);
            return "Книга " + book.getName() + " добавлена";
        }
        return "Книга с id " + book.getId() + " уже существует";
    }

    @Override
    public boolean isUniqueName(String name) {
        List<Book> list = repository.findAll();
        for (Book  book: list){
            String curBookName = book.getName();
            if(curBookName.equals(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public byte[] getImage(Long id) {
        Book book = repository.findOne(id);
        try {
            InputStream inputStream = new ByteArrayInputStream(book.getImage());
            BufferedImage img = ImageIO.read(inputStream);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", bos);
            return bos.toByteArray();
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public boolean addContent(Book book, byte[] content) {
        try {
            contentRepository.saveAndFlush(new Content(book.getId(),book.getName(),content));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public long getListLenghtByAuthor(int authorId) {
        Author author = authorRepository.findOne(authorId);
        return repository.findBooksByAuthor(author).size();
    }

    @Override
    public long getListLenghtByGenre(int genreId) {
        Genre genre = genreRepository.findOne(genreId);
        return repository.findBooksByGenre(genre).size();
    }

    @Override
    public long getListLengthBySearch(String searchQuery) {
        String query = "%"+searchQuery+"%";
        return repository.findBooksBySearchQuery(query).size();
    }

    @Override
    public List<Book> getBooksByRandom(int limit) {
        return repository.findBooksByRandom(new PageRequest(0,limit));
    }

    @Override
    public byte[] getContent(String bookName) {
        return contentRepository.getContentByBookName(bookName).getContent();
    }

}
