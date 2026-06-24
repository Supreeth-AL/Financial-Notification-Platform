package com.zetheta.processor.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.zetheta.processor.entity.UserPreference;
import com.zetheta.processor.service.UserPreferenceService;

@RestController

@RequestMapping("/api/preferences")
public class UserPreferenceController {

    private final
    UserPreferenceService service;

    public UserPreferenceController(
            UserPreferenceService service) {

        this.service = service;
    }

    @PostMapping
    public UserPreference savePreference(
            @RequestBody
            UserPreference preference) {

        return service.savePreference(
                preference);
    }

    @GetMapping("/{userId}")
    public Optional<UserPreference>
    getPreference(
            @PathVariable String userId) {

        return service.getPreference(
                userId);
    }
}