package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.todolist.service.todoitemservice;

@Controller
public class HomeController {

	@Autowired
	private todoitemservice  toservice;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", toservice.getAll());
        return modelAndView;
    }
}