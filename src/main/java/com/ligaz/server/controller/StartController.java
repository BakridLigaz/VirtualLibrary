package com.ligaz.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView getView() {
        return new ModelAndView("index");
    }

}
