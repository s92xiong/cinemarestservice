package com.cinemaroomrestservice.cinemarestservice.controller;

import com.cinemaroomrestservice.cinemarestservice.dto.TheatreStats;
import com.cinemaroomrestservice.cinemarestservice.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getTheatreStats(@RequestParam(value = "password", required = false) String password) {
        Map<String, String> errorResponse = new HashMap<>();
        if ("super_secret".equals(password)) {
            TheatreStats statistics = statsService.getStats();
            return ResponseEntity.ok(statistics);
        } else {
            errorResponse.put("error", "The password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
