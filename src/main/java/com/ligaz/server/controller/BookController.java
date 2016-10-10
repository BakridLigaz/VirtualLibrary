package com.ligaz.server.controller;

import com.ligaz.server.entity.Author;
import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Content;
import com.ligaz.server.entity.Genre;
import com.ligaz.server.service.BookService;
import com.ligaz.server.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private ContentService contentService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Book> getAll() {
        return service.getAllBooks();
    }

    @RequestMapping(value = "/getByAuthor", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Book> getByAuthor(@RequestBody Author author) {
        return service.getBooksByAuthor(author);
    }

    @RequestMapping(value = "/getByGenre", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Book> getByGenre(@RequestBody Genre genre) {
        return service.getBooksByGenre(genre);
    }

    @RequestMapping(value = "/getBySearch/{query}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Book> getBySearch(@PathVariable("query") String query) {
        return service.getBooksBySearch(query);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean addBook(@RequestBody Book book) {
        try {
            service.addBook(book);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public
    @ResponseBody
    String updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    boolean deleteBook(@PathVariable("id") long id) {
        try {
            contentService.deleteContent(service.getBook(id));
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Book getBookbyId(@PathVariable("id") long id) {
        return service.getBook(id);
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    Book getBookbyName(@PathVariable("name") String name) {
        return service.getBookByName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addImage", consumes = "multipart/form-data")
    public
    @ResponseBody
    boolean setImage(@RequestParam(value = "file", required = false) MultipartFile file,
                     @RequestParam(value = "name") String bookName
    ) {
        try {
            Book curBook = service.getBookByName(bookName);
            curBook.setImage(file.getBytes());
            service.updateBook(curBook);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addContent", consumes = "multipart/form-data")
    public
    @ResponseBody
    boolean setContent(@RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam(value = "name") String bookName
    ) {
        try {
            Book curBook = service.getBookByName(bookName);
            Content curContent = new Content(curBook, file.getBytes());
            contentService.addContent(curContent);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }



}
