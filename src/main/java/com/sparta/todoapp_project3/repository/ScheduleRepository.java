package com.sparta.todoapp_project3.repository;

import com.sparta.todoapp_project3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
