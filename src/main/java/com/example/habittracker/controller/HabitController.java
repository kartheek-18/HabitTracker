package com.example.habittracker.controller;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitImage;
import com.example.habittracker.repo.HabitRepository;
import com.example.habittracker.repo.HabitImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HabitController {

    @Autowired
    private HabitRepository habitRepo;

    @Autowired
    private HabitImageRepository habitImageRepo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("habits", habitRepo.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addHabit(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam(required = false) Integer targetDays) {
        Habit habit = new Habit();
        habit.setTitle(title);
        habit.setDescription(description);
        habit.setTargetDays(targetDays != null ? targetDays : 0);
        habit.setCurrentStreak(0);
        habitRepo.save(habit);
        return "redirect:/";
    }

    @PostMapping("/mark/{id}")
    public String markDone(@PathVariable Long id) {
        Habit habit = habitRepo.findById(id).orElseThrow();
        habit.setCurrentStreak(habit.getCurrentStreak() + 1);
        habitRepo.save(habit);
        return "redirect:/";
    }

    @GetMapping("/habit/{id}")
    public String habitDetail(@PathVariable Long id, Model model) {
        Habit habit = habitRepo.findById(id).orElseThrow();
        List<HabitImage> images = habitImageRepo.findByHabitId(id);
        Map<LocalDate, List<HabitImage>> grouped = images.stream()
                .collect(Collectors.groupingBy(HabitImage::getDate));
        model.addAttribute("habit", habit);
        model.addAttribute("imageGroups", grouped);
        return "habit-detail";
    }

    @PostMapping("/habit/{id}/upload")
    public String uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) throws IOException {
        Habit habit = habitRepo.findById(id).orElseThrow();
        String folder = "uploads/" + id;
        Files.createDirectories(Paths.get(folder));
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path path = Paths.get(folder, fileName);
        Files.write(path, file.getBytes());

        HabitImage img = new HabitImage();
        img.setFileName(fileName);
        img.setDate(LocalDate.now());
        img.setHabit(habit);
        habitImageRepo.save(img);

        return "redirect:/habit/" + id;
    }
}