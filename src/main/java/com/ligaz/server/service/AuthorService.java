package com.ligaz.server.service;

import com.ligaz.server.entity.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> getAll();
    public Author getAuthor(int id);
    public void addAuthor(Author author);
    public void updateAuthor(Author author);
}
