package com.ligaz.server.controller;

import com.ligaz.server.entity.Genre;
import com.ligaz.server.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService service;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public
    @ResponseBody
    List<Genre> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public
    @ResponseBody
    Genre getGenre(@PathVariable("id") int id) {
        return service.getGenre(id);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String addGenre(@RequestBody Genre genre) {
        return service.addGenre(genre);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public String updateGenre(@RequestBody Genre genre) {
        return service.updateGenre(genre);
    }

}
