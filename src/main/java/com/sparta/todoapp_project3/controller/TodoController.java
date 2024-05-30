package com.sparta.todoapp_project3.controller;

import com.sparta.todoapp_project3.repository.Todo;
import com.sparta.todoapp_project3.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1.0/todo")
@RestController
@AllArgsConstructor
public class TodoController {

    public final TodoService todoService;

    @PostMapping
        public ResponseEntity<TodoResponseDTO> postTodo(@RequestBody TodoRequestDTO dto) {
            Todo todo = todoService.createTodo(dto);
            TodoResponseDTO response = new TodoResponseDTO(todo);
            return ResponseEntity.ok().body(response);
        }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDTO> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDTO> response = todos.stream()
                .map(TodoResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDTO> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        todoService.deleteTodo(todoId, dto.getPassword());
        return ResponseEntity.ok().build();
    }
}