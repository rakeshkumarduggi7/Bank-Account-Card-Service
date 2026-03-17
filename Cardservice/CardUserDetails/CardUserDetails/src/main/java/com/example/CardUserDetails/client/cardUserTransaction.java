package com.example.CardUserDetails.client;

import com.example.CardUserDetails.Model.userTransactions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="UserTransactionDetails")
public interface cardUserTransaction {
    @GetMapping("/transactions/getalltransactions")
    List<userTransactions>  getalltransactions(@RequestParam("AccountNo") int AccountNo);

    @PostMapping("/transactions/addtransaction")
    void addTransaction(@RequestBody userTransactions ut);
}
