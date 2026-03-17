package com.example.UserTransactionDetails.Repository;


import com.example.UserTransactionDetails.Model.userTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userTransactionsRepository extends JpaRepository<userTransactions,Integer> {

    @Query(value = "select * from user_Transactions where accountno=:accountno",nativeQuery = true)
    List<userTransactions> getTransactions(@Param("accountno") int accountno);
}
