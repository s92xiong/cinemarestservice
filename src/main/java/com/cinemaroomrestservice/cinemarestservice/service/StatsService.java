package com.cinemaroomrestservice.cinemarestservice.service;

import com.cinemaroomrestservice.cinemarestservice.config.TheatreDimensions;
import com.cinemaroomrestservice.cinemarestservice.dto.TheatreStats;
import com.cinemaroomrestservice.cinemarestservice.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private TheatreStats stats;

    public StatsService() {
        var numSeats = TheatreDimensions.ROWS.getValue() * TheatreDimensions.COLUMNS.getValue();
        this.stats = new TheatreStats(0, numSeats, 0);
    }

    public synchronized void purchaseTicket(Ticket ticket) {
        stats.setCurrent_income(stats.getCurrent_income() + ticket.getPrice());
        stats.setNumber_of_available_seats(stats.getNumber_of_available_seats() - 1);
        stats.setNumber_of_purchased_tickets(stats.getNumber_of_purchased_tickets() + 1);
    }

    public synchronized void refundTicket(Ticket ticket) {
        stats.setCurrent_income(stats.getCurrent_income() - ticket.getPrice());
        stats.setNumber_of_available_seats(stats.getNumber_of_available_seats() + 1);
        stats.setNumber_of_purchased_tickets(stats.getNumber_of_purchased_tickets() - 1);
    }

    public TheatreStats getStats() {
        return stats;
    }
}
