package com.cinemaroomrestservice.cinemarestservice.service;

import com.cinemaroomrestservice.cinemarestservice.config.TheatreDimensions;
import com.cinemaroomrestservice.cinemarestservice.model.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    private List<Seat> seatList;

    // In an actual app, this service would most likely be getting data from a relational DB
    public SeatService() {
        int total_rows = TheatreDimensions.ROWS.getValue();
        int total_columns = TheatreDimensions.COLUMNS.getValue();

        List<Seat> seatList = new ArrayList<>();

        for (int row = 1; row <= total_rows; row++) {
            for (int col = 1; col <= total_columns; col++) {
                // Rows 4 and down cost $10, while all rows 5 and up cost $8
                var price = (row <= 4) ? 10 : 8;
                Seat seat = new Seat(row, col, price);
                seatList.add(seat);
            }
        }

        this.seatList = seatList;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }
}