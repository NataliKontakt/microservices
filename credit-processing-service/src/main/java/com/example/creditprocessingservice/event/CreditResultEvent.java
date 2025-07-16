package com.example.creditprocessingservice.event;

import java.io.Serializable;
import java.util.UUID;

public class CreditResultEvent implements Serializable {
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

    public enum Status {
        APPROVED, REJECTED
    }

    private UUID id;
    private Status status;


}
