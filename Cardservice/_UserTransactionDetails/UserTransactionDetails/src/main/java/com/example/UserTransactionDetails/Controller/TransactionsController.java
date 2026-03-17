package com.example.UserTransactionDetails.Controller;


import com.example.UserTransactionDetails.Model.userTransactions;
import com.example.UserTransactionDetails.Service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
TransactionsService service;

    @GetMapping("/getalltransactions")
      List<userTransactions>  getalltransactions(@RequestParam("AccountNo") int AccountNo){

 return    service.getTransactionHistory(AccountNo) ;

    }
    @PostMapping("/addtransaction")
    void addTransaction(@RequestBody userTransactions ut){
        service.addTransaction(ut);
    }

}
