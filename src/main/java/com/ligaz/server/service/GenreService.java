package com.ligaz.server.service;

import com.ligaz.server.entity.Genre;

import java.util.List;

public interface GenreService {
    public List<Genre> getAll();
    public Genre getGenre(int id);
    public void addGenre(Genre genre);
    public void updateGenre(Genre genre);
}
