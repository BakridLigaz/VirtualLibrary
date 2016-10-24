package com.ligaz.server.service;

import com.ligaz.server.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getGenre(int id);

    String addGenre(Genre genre);

    String updateGenre(Genre genre);

    boolean isUniqueName(String name);

    boolean isUsed(int id);

    String deleteGenre(int id);
}
