package com.ligaz.server.service;

import com.ligaz.server.entity.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> getAll();
    public Author getAuthor(int id);

    public String addAuthor(Author author);

    public String updateAuthor(Author author);
}
