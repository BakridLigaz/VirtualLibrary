package com.ligaz.server.controller;

import com.ligaz.server.entity.Genre;
import com.ligaz.server.repository.GenreRepository;
import com.ligaz.server.service.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    GenreServiceImpl service;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Genre> getAll(){
        return service.getAll();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Genre getGenre(@PathVariable("id") int id){
        return service.getGenre(id);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void addGenre(@RequestBody Genre genre){
        service.addGenre(genre);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public void updateGenre(@RequestBody Genre genre){
        service.updateGenre(genre);
    }
}
