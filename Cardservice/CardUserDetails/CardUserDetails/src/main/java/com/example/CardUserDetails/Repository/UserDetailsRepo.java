package com.example.CardUserDetails.Repository;


import com.example.CardUserDetails.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails,Integer> {

    @Query(value = "select count(*) from userdetails where name=:username",nativeQuery = true)
    Long signUpValidityCheck(@Param("username")String username);

    @Query(value = "select count(*) from   userdetails where cardno=:cardno",nativeQuery = true)

    Long verifyNewCardNo(@Param("cardno") int cardno);
    @Query(value = "select count(*) from   userdetails where name=:username and password=:password",nativeQuery = true)


    Long loginVerify(@Param("username") String username, @Param("password") String password);

    @Query(value = "select count(*) from userdetails where cardno=:accountnumber",nativeQuery = true)
    Long accountExists(@Param("accountnumber")Long accountnumber);
}
