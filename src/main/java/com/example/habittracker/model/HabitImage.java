package com.example.habittracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HabitImage {
    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    private LocalDate date;

    @ManyToOne
    private Habit habit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Habit getHabit() {
		return habit;
	}

	public void setHabit(Habit habit) {
		this.habit = habit;
	}

    // getters and setters
    
}