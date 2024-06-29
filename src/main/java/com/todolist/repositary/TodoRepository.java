package com.todolist.repositary;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.entity.TodoItem;

@Repository

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
    // Define custom query methods if needed
}
