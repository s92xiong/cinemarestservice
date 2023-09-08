package com.cinemaroomrestservice.cinemarestservice.dto;

import com.cinemaroomrestservice.cinemarestservice.model.Ticket;

import java.util.UUID;

public class PurchaseConfirmation {
    private UUID token;
    private Ticket ticket;

    public PurchaseConfirmation(UUID token, Ticket ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
