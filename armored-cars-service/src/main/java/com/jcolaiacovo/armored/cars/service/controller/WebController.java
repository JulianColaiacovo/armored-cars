package com.jcolaiacovo.armored.cars.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @RequestMapping(value = "/web", method = RequestMethod.GET)
    public ModelAndView web(HttpServletRequest request) {
        return new ModelAndView("application");
    }

}