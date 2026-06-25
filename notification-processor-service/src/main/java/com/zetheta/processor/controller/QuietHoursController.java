package com.zetheta.processor.controller;

import org.springframework.web.bind.annotation.*;

import com.zetheta.processor.entity.QuietHoursPreference;
import com.zetheta.processor.repository.QuietHoursPreferenceRepository;

@RestController

@RequestMapping("/api/quiet-hours")
public class QuietHoursController {

    private final
    QuietHoursPreferenceRepository repository;

    public QuietHoursController(
            QuietHoursPreferenceRepository repository) {

        this.repository = repository;
    }

    @PostMapping
    public QuietHoursPreference save(
            @RequestBody
            QuietHoursPreference preference) {

        return repository.save(preference);
    }
}