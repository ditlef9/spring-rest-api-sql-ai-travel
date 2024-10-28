package com.ekeberg.spring_rest_api_sql_ai_travel.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String messsage;
    private String details;

    public ErrorDetails(LocalDateTime timestamp, String messsage, String details) {
        super();
        this.timestamp = timestamp;
        this.messsage = messsage;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMesssage() {
        return messsage;
    }

    public String getDetails() {
        return details;
    }

}
