package com.example.CardUserDetails.Exceptions;

public class lowBalanceToWithDrawMoney extends RuntimeException{
    public lowBalanceToWithDrawMoney(String message) {
        super(message);
    }
}
