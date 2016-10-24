package com.ligaz.server.service;

import com.ligaz.server.entity.Book;
import com.ligaz.server.entity.Genre;
import com.ligaz.server.repository.BookRepository;
import com.ligaz.server.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository repository;

    @Autowired
    BookRepository bookRepository;

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

    @Override
    public boolean isUniqueName(String name) {
        List<Genre> list = repository.findAll();
        for (Genre genre: list){
            String curGenreName = genre.getName();
            if(curGenreName.equals(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isUsed(int id) {
        Genre genre  = repository.findOne(id);
        List<Book> books = bookRepository.findBooksByGenre(genre);
        if(books.isEmpty()){
            return false;
        }else return true;

    }

    @Override
    public String deleteGenre(int id) {
        try {
            Genre genre = repository.findOne(id);
            repository.delete(id);
            return "Жанр "+genre.getName()+" успешно удален";
        }catch (Exception ex){
            ex.printStackTrace();
            return "Жанр не удален";
        }
    }
}
