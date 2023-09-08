package com.cinemaroomrestservice.cinemarestservice.dto;

import java.util.UUID;

public class RefundRequest {
    private UUID token;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
