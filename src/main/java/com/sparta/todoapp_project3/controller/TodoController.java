package com.sparta.todoapp_project3.controller;

import com.sparta.todoapp_project3.repository.Todo;
import com.sparta.todoapp_project3.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TodoController {

    public final TodoService todoService;

    @PostMapping("/v1.0/todo")
        public ResponseEntity<TodoResponseDTO> postTodo(@RequestBody TodoRequestDTO dto) {
            Todo todo = todoService.createTodo(dto);
            TodoResponseDTO response = new TodoResponseDTO(todo);
            return ResponseEntity.ok().body(response);
        }
    }