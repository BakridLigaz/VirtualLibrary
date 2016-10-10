package com.ligaz.server.controller;

import com.ligaz.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    BookService bookService;


}
