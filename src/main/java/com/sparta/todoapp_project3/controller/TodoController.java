package com.sparta.todoapp_project3.controller;

import com.sparta.todoapp_project3.dto.TodoRequestDto;
import com.sparta.todoapp_project3.dto.TodoResponseDto;
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
        public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto dto) {
            Todo todo = todoService.createTodo(dto);
            TodoResponseDto response = new TodoResponseDto(todo);
            return ResponseEntity.ok().body(response);
        }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDto> response = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        todoService.deleteTodo(todoId, dto.getPassword());
        return ResponseEntity.ok().build();
    }
}