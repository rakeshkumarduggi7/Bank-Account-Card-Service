package com.example.CardUserDetails.Exceptions;

public class ResponseStructure {
    String Error;
    String message;

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
