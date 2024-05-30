package com.sparta.todoapp_project3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getter methods
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter methods
    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
