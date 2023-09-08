package com.cinemaroomrestservice.cinemarestservice.dto;

import com.cinemaroomrestservice.cinemarestservice.model.Ticket;

public class RefundConfirmation {
    private Ticket returned_ticket;
    public RefundConfirmation(Ticket ticket) {
        this.returned_ticket = ticket;
    }

    public Ticket getReturned_ticket() {
        return returned_ticket;
    }
}
