package com.cinemaroomrestservice.cinemarestservice.controller;

import com.cinemaroomrestservice.cinemarestservice.dto.RefundConfirmation;
import com.cinemaroomrestservice.cinemarestservice.dto.RefundRequest;
import com.cinemaroomrestservice.cinemarestservice.model.Seat;
import com.cinemaroomrestservice.cinemarestservice.model.Ticket;
import com.cinemaroomrestservice.cinemarestservice.service.SeatService;
import com.cinemaroomrestservice.cinemarestservice.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RefundController {
    private final SeatService seatService;
    private StatsService statsService;

    public RefundController(SeatService seatService, StatsService statsService) {
        this.seatService = seatService;
        this.statsService = statsService;
    }

    public static Optional<Seat> findSeatByToken(List<Seat> seatList, UUID targetToken) {
        return seatList.stream()
                .filter(seat -> seat.getToken() != null && seat.getToken().equals(targetToken))
                .findFirst();
    }

    @PostMapping("/return")
    public ResponseEntity<?> refundSeat(@RequestBody RefundRequest refundRequest) {
        Map<String, String> errorResponse = new HashMap<>();

        List<Seat> seatList = seatService.getAllSeats();

        UUID token = refundRequest.getToken();
        Optional<Seat> foundSeat = findSeatByToken(seatList, token);

        if (foundSeat.isEmpty()) {
            errorResponse.put("error", "Wrong token!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Seat seat = foundSeat.get();
        seat.setOccupied(false);
        seat.setToken(null);

        Ticket ticket = seat.getTicket();
        RefundConfirmation confirmation = new RefundConfirmation(ticket);

        // Update stats
        statsService.refundTicket(ticket);

        return ResponseEntity.ok(confirmation);
    }
}
