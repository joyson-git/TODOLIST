package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todolist.entity.TodoItem;
import com.todolist.service.todoitemservice;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class todofromController {

	   @Autowired
	    private todoitemservice toservice;

	    @GetMapping("/create-todo")
	    public String showCreateForm(TodoItem todoItem) {
	        return "new-todo-item";
	    }

	    @PostMapping("/todo")
	    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, RedirectAttributes redirectAttributes) {
	        if (result.hasErrors()) {
	            return "new-todo-item";
	        }

	        toservice.save(todoItem);
	        redirectAttributes.addFlashAttribute("message", "Todo item created successfully");
	        return "redirect:/";
	    }
	}