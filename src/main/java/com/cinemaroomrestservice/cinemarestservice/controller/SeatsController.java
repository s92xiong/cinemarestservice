package com.cinemaroomrestservice.cinemarestservice.controller;

import com.cinemaroomrestservice.cinemarestservice.model.Seat;
import com.cinemaroomrestservice.cinemarestservice.service.SeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatsController {

    private final SeatService seatService;

    public SeatsController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<Seat> getSeats() {
        return seatService.getAllSeats();
    }
}
