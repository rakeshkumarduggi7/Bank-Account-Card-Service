package com.example.CardUserDetails.Exceptions;

public class Wrong_Login_Details extends RuntimeException{

  public   Wrong_Login_Details(String message){
        super(message);
    }
}
