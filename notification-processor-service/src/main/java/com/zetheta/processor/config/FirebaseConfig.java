package com.zetheta.processor.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() throws IOException {

        FirebaseOptions options =
                FirebaseOptions.builder()
                .setCredentials(
                        GoogleCredentials
                                .fromStream(
                                        getClass()
                                        .getResourceAsStream(
                                                "/firebase-service-account.json")))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {

            FirebaseApp.initializeApp(options);

            System.out.println(
                    "Firebase Initialized");
        }
    }
}