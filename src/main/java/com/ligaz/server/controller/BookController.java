package com.ligaz.server.controller;

import com.ligaz.server.entity.Book;
import com.ligaz.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAll(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return service.getAllBooks(offset, limit);
    }

    @RequestMapping(value = "/randomBooks", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getRandom(@RequestParam("limit") int limit) {
        return service.getBooksByRandom(limit);
    }

    @RequestMapping(value = "/getByAuthor", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getByAuthor(@RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("id") int authorId) {
        return service.getBooksByAuthor(offset, limit, authorId);
    }

    @RequestMapping(value = "/getByGenre", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getByGenre(@RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("id") int genreId) {
        return service.getBooksByGenre(offset, limit, genreId);
    }

    @RequestMapping(value = "/getBySearch/", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getBySearch(@RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("query") String query) {
        return service.getBooksBySearch(offset, limit, query);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public boolean addBook(@RequestBody Book book) {
        try {
            service.addBook(book);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public String updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBook(@PathVariable("id") long id) {
        try {
            Book curBook = service.getBook(id);
            service.deleteBook(id);
            return "Книга " + curBook.getName() + " успешно удалена";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Книга не удалена";
        }

    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBookById(@PathVariable("id") long id) {
        return service.getBook(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addImage", consumes = "multipart/form-data")
    @ResponseBody
    public boolean setImage(@RequestParam(value = "file") MultipartFile file,
                            @RequestParam(value = "name") String bookName
    ) {
        try {
            Book curBook = service.getBookByName(bookName);
            curBook.setImage(file.getBytes());
            service.updateBook(curBook);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/addContent", consumes = "multipart/form-data")
    @ResponseBody
    public boolean setContent(@RequestParam(value = "file") MultipartFile file,
                              @RequestParam(value = "name") String bookName
    ) {
        try {
            Book curBook = service.getBookByName(bookName);
            service.addContent(curBook, file.getBytes());
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @RequestMapping(value = "/isUnique/{name}", method = RequestMethod.GET)
    @ResponseBody
    public boolean isUnique(@PathVariable String name) {
        return service.isUniqueName(name);
    }

    @RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return service.getImage(id);
    }

    @RequestMapping(value = "/sizeListAll", method = RequestMethod.GET)
    @ResponseBody
    public long getSize() {
        return service.getAllListLenght();
    }

    @RequestMapping(value = "/sizeListByAuthor/{authorId}", method = RequestMethod.GET)
    @ResponseBody
    public long getSizeListByAuthor(@PathVariable("authorId") int authorId) {
        return service.getListLenghtByAuthor(authorId);
    }

    @RequestMapping(value = "/sizeListByGenre/{genreId}", method = RequestMethod.GET)
    public
    @ResponseBody
    long getSizeListByGenre(@PathVariable("genreId") int genreId) {
        return service.getListLenghtByGenre(genreId);
    }

    @RequestMapping(value = "/sizeListBySearch/", method = RequestMethod.GET)
    @ResponseBody
    public long getSizeListBySearch(@RequestParam("query") String query) {
        return service.getListLengthBySearch(query);
    }

    @RequestMapping(value = "/getContent/", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public byte[] getPdf(@RequestParam("bookName") String bookName) {
        return service.getContent(bookName);
    }

}
