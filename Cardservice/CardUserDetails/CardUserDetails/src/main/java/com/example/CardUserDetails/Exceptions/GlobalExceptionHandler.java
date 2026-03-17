package com.example.CardUserDetails.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Wrong_Login_Details.class)
    public ResponseEntity<ResponseStructure> badlogindetails(Wrong_Login_Details wd){
            ResponseStructure rs=new ResponseStructure();
            rs.setMessage(wd.getMessage());
            rs.setError("Unauthorized , dont u have the respect for Authoritat");
            return new  ResponseEntity<>(rs, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(lowBalanceToWithDrawMoney.class)
    public ResponseEntity<ResponseStructure> withdrawError(lowBalanceToWithDrawMoney  wde){
        ResponseStructure rs=new ResponseStructure();
        rs.setMessage(wde.getMessage());
        rs.setError("Money bad / low to withdraw,/ BAD REQUEST");
        return new  ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseStructure> inputmismatch( ){
        ResponseStructure rs=new ResponseStructure();
        rs.setMessage("Input wrong");
        rs.setError("IllegalArgumentException");
        return new  ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
    }
}
