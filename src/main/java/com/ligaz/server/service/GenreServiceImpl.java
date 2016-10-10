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
    public String addGenre(Genre genre) {
        if(!repository.exists(genre.getId())){
            repository.saveAndFlush(genre);
            return "Жанр " + genre.getName() + " успешно добавлен";
        }
        return "Жанр " + genre.getName() + " не был добавлен, поскольку данный id уже занят";
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
    public String updateGenre(Genre genre) {
        if(repository.exists(genre.getId())){
            repository.saveAndFlush(genre);
            return "Жанр " + genre.getName() + " был успешно изменен";
        }
        return "Жанра  с id " + genre.getId() + " нет в базе";
    }
}
