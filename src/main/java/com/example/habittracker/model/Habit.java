package com.example.habittracker.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Habit {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private int targetDays;
    private int currentStreak;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    private List<HabitImage> images;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTargetDays() {
		return targetDays;
	}

	public void setTargetDays(int targetDays) {
		this.targetDays = targetDays;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	public List<HabitImage> getImages() {
		return images;
	}

	public void setImages(List<HabitImage> images) {
		this.images = images;
	}

    // getters and setters
    
}