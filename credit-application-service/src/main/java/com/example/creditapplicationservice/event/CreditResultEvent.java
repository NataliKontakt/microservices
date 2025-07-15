package com.example.creditapplicationservice.event;

import java.util.UUID;
import com.example.creditapplicationservice.model.CreditApplication.Status;

public class CreditResultEvent {
    private UUID id;
    private Status status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
