package com.sparta.todoapp_project3.controller;

import com.sparta.todoapp_project3.entity.Comment;
import com.sparta.todoapp_project3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestParam Long scheduleId,
                                        @RequestParam String content,
                                        @RequestParam Long userId) {
        try {
            Comment comment = commentService.addComment(scheduleId, content, userId);
            return ResponseEntity.ok(comment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
