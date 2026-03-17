package com.example.CardUserDetails.Repository;

import com.example.CardUserDetails.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository< UserInfo,Integer> {


    @Query(value = "select * from user_info where username=:username",nativeQuery = true)
    UserInfo getUserInfo(@Param("username") String username );


    @Query(value = "select * from user_info where username=:username",nativeQuery = true)

    void addMoneyToAccount(int accountnumber, int amount);
    @Query(value = "select * from user_info where cardno=:accountnumber",nativeQuery = true)

    UserInfo getAccountInfo(@Param("accountnumber")int accountnumber);
}
