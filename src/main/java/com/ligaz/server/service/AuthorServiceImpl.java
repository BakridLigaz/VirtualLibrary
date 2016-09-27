package com.ligaz.server.service;

import com.ligaz.server.entity.Author;
import com.ligaz.server.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Author getAuthor(int id) {
        return repository.findOne(id);
    }

    @Override
    public void addAuthor(Author author) {
        if(!repository.exists(author.getId())){
            repository.saveAndFlush(author);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        if(repository.exists(author.getId())){
            repository.saveAndFlush(author);
        }

    }
}
