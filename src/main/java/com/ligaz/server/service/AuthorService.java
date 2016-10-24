package com.ligaz.server.service;

import com.ligaz.server.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getAuthor(int id);

    String addAuthor(Author author);

    String updateAuthor(Author author);

    boolean isUniqueName(String name);

    boolean isUsed(int id);

    String delete(int id);
}
