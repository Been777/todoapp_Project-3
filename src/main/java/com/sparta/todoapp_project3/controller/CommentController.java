package com.sparta.todoapp_project3.controller;

import com.sparta.todoapp_project3.entity.Comment;
import com.sparta.todoapp_project3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestParam Long scheduleId,
                                        @RequestParam String content,
                                        @RequestParam String userId) {
        try {
            Comment comment = commentService.addComment(scheduleId, content, userId);
            return ResponseEntity.ok(comment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 수정 엔드포인트
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestParam Long scheduleId,
                                           @PathVariable Long commentId,
                                           @RequestParam String content,
                                           @RequestParam String userId) {
        try {
            Comment updatedComment = commentService.updateComment(scheduleId, commentId, content, userId);
            return ResponseEntity.ok(updatedComment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 삭제 엔드포인트
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestParam Long scheduleId,
                                           @PathVariable Long commentId,
                                           @RequestParam String userId) {
        try {
            commentService.deleteComment(scheduleId, commentId, userId);
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
