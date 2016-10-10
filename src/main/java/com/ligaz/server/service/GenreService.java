package com.ligaz.server.service;

import com.ligaz.server.entity.Genre;

import java.util.List;

public interface GenreService {
    public List<Genre> getAll();
    public Genre getGenre(int id);

    public String addGenre(Genre genre);

    public String updateGenre(Genre genre);
}
