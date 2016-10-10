package com.ligaz.server.controller;

import com.ligaz.server.entity.Author;
import com.ligaz.server.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Author> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author getAuthor(@PathVariable("id") int id) {
        return service.getAuthor(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addAuthor(@RequestBody Author author) {
        return service.addAuthor(author);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String updateAuthor(@RequestBody Author author) {
        return service.updateAuthor(author);
    }
}
