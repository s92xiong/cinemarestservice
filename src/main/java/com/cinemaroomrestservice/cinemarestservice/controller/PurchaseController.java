package com.cinemaroomrestservice.cinemarestservice.controller;

import com.cinemaroomrestservice.cinemarestservice.config.TheatreDimensions;
import com.cinemaroomrestservice.cinemarestservice.dto.PurchaseConfirmation;
import com.cinemaroomrestservice.cinemarestservice.dto.SeatDTO;
import com.cinemaroomrestservice.cinemarestservice.model.Seat;
import com.cinemaroomrestservice.cinemarestservice.model.Ticket;
import com.cinemaroomrestservice.cinemarestservice.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class PurchaseController {
    SeatService seatService;

    public PurchaseController(SeatService seatService) {
        this.seatService = seatService;
    }
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody SeatDTO seatDTO) {
        var row = seatDTO.getRow();
        var col = seatDTO.getColumn();

        int total_rows = TheatreDimensions.ROWS.getValue();
        int total_columns = TheatreDimensions.COLUMNS.getValue();

        Map<String, String> errorResponse = new HashMap<>();

        // Prevent purchasing of an out-of-bounds seat
        if (row < 1 || row > total_rows || col < 1 || col > total_columns) {
            errorResponse.put("error", "The number of a row or a column is out of bounds!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        List<Seat> seatList =  seatService.getAllSeats();
        Seat seat = seatList.get((row - 1) * total_columns + (col - 1));
        Ticket ticket = seat.getTicket();

        // Prevent purchasing of an already purchased seat
        if (seat.isOccupied()) {
            errorResponse.put("error", "The ticket has been already purchased!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Update seat
        seat.setOccupied(true);
        seat.setToken(UUID.randomUUID());

        PurchaseConfirmation confirmation = new PurchaseConfirmation(seat.getToken(), ticket);

        // Show token in response body
        return ResponseEntity.ok(confirmation);
    }
}
