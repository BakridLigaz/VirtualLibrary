package com.ligaz.server.service;

import com.ligaz.server.entity.Genre;
import com.ligaz.server.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository repository;

    @Override
    public void addGenre(Genre genre) {
        if(!repository.exists(genre.getId())){
            repository.saveAndFlush(genre);
        }
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public Genre getGenre(int id) {
        return repository.findOne(id);
    }

    @Override
    public void updateGenre(Genre genre) {
        if(repository.exists(genre.getId())){
            repository.saveAndFlush(genre);
        }
    }
}
