package com.sparta.todoapp_project3.repository;

import com.sparta.todoapp_project3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
