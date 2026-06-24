package com.zetheta.processor.controller;

import org.springframework.web.bind.annotation.*;

import com.zetheta.processor.entity.DndPreference;
import com.zetheta.processor.repository.DndPreferenceRepository;

@RestController

@RequestMapping("/api/dnd")
public class DndController {

    private final
    DndPreferenceRepository repository;

    public DndController(
            DndPreferenceRepository repository) {

        this.repository = repository;
    }

    @PostMapping
    public DndPreference save(
            @RequestBody
            DndPreference preference) {

        return repository.save(
                preference);
    }
}