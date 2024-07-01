package com.todolist.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.todolist.entity.TodoItem;
import org.springframework.ui.Model;
import com.todolist.service.todoitemservice;


import jakarta.validation.Valid;

@Controller
public class todofromController {

    @Autowired
    private todoitemservice todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());
        todoItemService.save(todoItem);
        return "redirect:/";
        
    }
  @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
    
        TodoItem todoItem = todoItemService
        		.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item with id " + id + " not found"));

        // Delete the retrieved TodoItem
        todoItemService.delete(todoItem);

        // Redirect to the homepage after deletion
        return "redirect:/";
    }

    
  
  @GetMapping("/edit/{id}")
  public String UpdateTodoItem(@PathVariable("id") Long id, Model model) {
	    
      TodoItem todoItem = todoItemService
      		.getById(id)
              .orElseThrow(() -> new IllegalArgumentException("Todo item with id " + id + " not found"));

      // Delete the retrieved TodoItem
     model.addAttribute("todo",todoItem);
     return "edit-todo";
     
  }

  @PostMapping("/todo/{id}")
  public String updateTodoItem(@PathVariable("id") Long id, 
                               @Valid TodoItem newTodoItem, 
                               BindingResult result, 
                               Model model) {
      // Check if there are validation errors
      if (result.hasErrors()) {
          model.addAttribute("todoItem", newTodoItem);
          return "editTodo"; // Replace with the actual view name for editing
      }
      
      // Fetch the existing TodoItem by id
      TodoItem existingTodoItem = todoItemService
              .getById(id)
              .orElseThrow(() -> new IllegalArgumentException("Todo item with id " + id + " not found"));
      
      // Update fields with new values
      existingTodoItem.setIsComplete(newTodoItem.getIsComplete());
      existingTodoItem.setDescription(newTodoItem.getDescription());
      
      // Save the updated TodoItem
      todoItemService.save(existingTodoItem);
      
      // Redirect to the appropriate view
      return "redirect:/";
  }

}
