package com.ligaz.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/books")
public class BookController {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public String getBook(ModelMap map){
        return "new Book";
    }
}
