package com.todolist.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.entity.TodoItem;
import com.todolist.repositary.TodoRepository;

@Service
public class todoitemservice {
	@Autowired
	private  TodoRepository todoItemreposiory;
	 public Iterable<TodoItem> getAll() {
	        return todoItemreposiory.findAll();
	    }

	    public TodoItem save(TodoItem todoItem) {
	        if (todoItem.getId() == null) {
	            todoItem.setCreateAt(Instant.now());
	        }
	        todoItem.setUpdateAt(Instant.now());
	        return todoItemreposiory.save(todoItem);
	    }

	    public Optional<TodoItem> getById(Long id) {
	        return todoItemreposiory.findById(id);
	    }

	    public void delete(TodoItem todoItem) {
	    	todoItemreposiory.delete(todoItem);
	    }
	}