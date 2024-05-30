package com.sparta.todoapp_project3.service;

import com.sparta.todoapp_project3.entity.Comment;
import com.sparta.todoapp_project3.entity.Schedule;
import com.sparta.todoapp_project3.repository.CommentRepository;
import com.sparta.todoapp_project3.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Long scheduleId, String content, Long userId) {
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
}
