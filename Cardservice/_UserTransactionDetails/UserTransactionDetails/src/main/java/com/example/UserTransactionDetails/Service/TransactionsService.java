package com.example.UserTransactionDetails.Service;

import com.example.UserTransactionDetails.Model.userTransactions;
import com.example.UserTransactionDetails.Repository.userTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    userTransactionsRepository transactionsRepo;

  public  List<userTransactions> getTransactionHistory(int accountno){
     return    transactionsRepo.getTransactions(accountno);
    }

    public void addTransaction(userTransactions ut) {
      transactionsRepo.save(ut);
    }
}
