package com.example.habittracker.repo;

import com.example.habittracker.model.HabitImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitImageRepository extends JpaRepository<HabitImage, Long> {
    List<HabitImage> findByHabitId(Long habitId);
}