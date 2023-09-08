package com.cinemaroomrestservice.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat extends Ticket {
    private boolean isOccupied;
    private UUID token;

    public Seat(int row, int column, int price) {
        super(row, column, price);
        this.isOccupied = false;
        this.token = null;
    }

    @JsonIgnore
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @JsonIgnore
    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Ticket getTicket() {
        return new Ticket(getRow(), getColumn(), getPrice());
    }
}
