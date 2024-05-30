package com.sparta.todoapp_project3.service;

import com.sparta.todoapp_project3.entity.Comment;
import com.sparta.todoapp_project3.entity.Schedule;
import com.sparta.todoapp_project3.jwt.JwtUtil;
import com.sparta.todoapp_project3.repository.CommentRepository;
import com.sparta.todoapp_project3.repository.ScheduleRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // 댓글 등록 메서드
    public Comment addComment(Long scheduleId, String content, String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        // 토큰 검증 및 사용자 식별
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        Long userId = Long.parseLong(claims.getSubject());

        // 입력 유효성 검증
        if (scheduleId == null) {
            throw new IllegalArgumentException("일정 ID를 입력해 주세요.");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해 주세요.");
        }

        // 일정 존재 여부 확인
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (!scheduleOptional.isPresent()) {
            throw new IllegalArgumentException("일정을 찾을 수 없습니다.");
        }

        Schedule schedule = scheduleOptional.get();

        // 댓글 등록
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setSchedule(schedule);

        return commentRepository.save(comment);
    }

    // 댓글 수정 메서드
    public Comment updateComment(Long scheduleId, Long commentId, String content, String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        // 토큰 검증 및 사용자 식별
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        Long userId = Long.parseLong(claims.getSubject());

        // 입력 유효성 검증
        if (scheduleId == null || commentId == null) {
            throw new IllegalArgumentException("일정 ID와 댓글 ID를 입력해 주세요.");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력해 주세요.");
        }

        // 일정 존재 여부 확인
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (!scheduleOptional.isPresent()) {
            throw new IllegalArgumentException("일정을 찾을 수 없습니다.");
        }

        // 댓글 존재 여부 확인
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (!commentOptional.isPresent()) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentOptional.get();

        // 댓글의 작성자 확인
        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        // 댓글 내용 수정
        comment.setContent(content);

        return commentRepository.save(comment);
    }

    // 댓글 삭제 메서드
    public void deleteComment(Long scheduleId, Long commentId, String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        // 토큰 검증 및 사용자 식별
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        Long userId = Long.parseLong(claims.getSubject());

        // 입력 유효성 검증
        if (scheduleId == null || commentId == null) {
            throw new IllegalArgumentException("일정 ID와 댓글 ID를 입력해 주세요.");
        }

        // 일정 존재 여부 확인
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (!scheduleOptional.isPresent()) {
            throw new IllegalArgumentException("일정을 찾을 수 없습니다.");
        }

        // 댓글 존재 여부 확인
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (!commentOptional.isPresent()) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentOptional.get();

        // 댓글의 작성자 확인
        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
