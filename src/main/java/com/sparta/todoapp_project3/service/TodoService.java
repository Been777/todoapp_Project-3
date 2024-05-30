package com.sparta.todoapp_project3.service;

import com.sparta.todoapp_project3.controller.TodoRequestDTO;
import com.sparta.todoapp_project3.repository.Todo;
import com.sparta.todoapp_project3.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 할일 생성
    public Todo createTodo(TodoRequestDTO dto) {
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }
}