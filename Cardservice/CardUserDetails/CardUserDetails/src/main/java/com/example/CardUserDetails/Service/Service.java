package com.example.CardUserDetails.Service;
import com.example.CardUserDetails.Exceptions.Wrong_Login_Details;
import com.example.CardUserDetails.Exceptions.lowBalanceToWithDrawMoney;
import com.example.CardUserDetails.Model.*;
import com.example.CardUserDetails.Repository.UserDetailsRepo;
import com.example.CardUserDetails.Repository.UserInfoRepo;
import com.example.CardUserDetails.client.cardUserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    UserDetailsRepo detailsRepo;
    @Autowired
    UserInfoRepo inforepo;
    @Autowired
    cardUserTransaction transactionClient;


    public boolean validDetails(String username) {
        return detailsRepo.signUpValidityCheck(username) == 0;
    }

    public void signUpUser(SigningDetails sud) {
        UserDetails ud = new UserDetails();
        ud.setName(sud.getUsername());
        ud.setPassword(sud.getPassword());
        Random rdm = new Random();
        int r = rdm.nextInt(100000, 999999);
        while (detailsRepo.verifyNewCardNo(r)!=0) {
            r = rdm.nextInt(100000, 999999);
        }
        ud.setCardno(r);
        ud.setPinNumber(0);
        detailsRepo.save(ud);
        UserInfo ui=new UserInfo();
        ui.setUsername(ud.getName());
        ui.setAmount(0);
        ui.setCardno(ud.getCardno());
        inforepo.save(ui);
    }


    public boolean loginVerification(SigningDetails sud) {
        if (detailsRepo.loginVerify(sud.getUsername(),sud.getPassword())==1){
            return true;
        }
        throw new Wrong_Login_Details("Username or password wrong");

    }


    public UserInfo getUserInfo(SigningDetails sud) {

        return inforepo.getUserInfo( sud.getUsername());
    }

    public void withDraw(int amount, String user,int accountno) {
            UserInfo ui=inforepo.getUserInfo(user);
            if (ui.getAmount()<amount){
                throw new lowBalanceToWithDrawMoney("low funds to withdraw");
            }
           ui.setAmount( ui.getAmount()-amount);
           inforepo.save(ui);
           userTransactions ut=new userTransactions();
           ut.setAccountno(accountno);
           ut.setAmount(amount);
           ut.setDate(LocalDateTime.now());
           ut.setTransactiontype("Withdraw");

           transactionClient.addTransaction(ut);

    }

    public void depositMoney(int money, String user,int accountno) {
        UserInfo ui=inforepo.getUserInfo(user);
        ui.setAmount( ui.getAmount()+money);
        inforepo.save(ui);
        
        userTransactions ut=new userTransactions();
           ut.setAccountno(accountno);
           ut.setAmount(money);
           ut.setDate(LocalDateTime.now());
           ut.setTransactiontype("deposit");

           transactionClient.addTransaction(ut);

    }

    public void transferMoney( int amount  , String user,int accountno,int tccountno) {
            UserInfo tui=inforepo.getAccountInfo(tccountno);
            if(tui!=null){
                    tui.setAmount(tui.getAmount()+amount);
                    inforepo.save(tui);
          UserInfo ui=inforepo.getUserInfo(user);
            if (ui.getAmount()<amount){
                throw new lowBalanceToWithDrawMoney("low funds to withdraw");
            }
           ui.setAmount( ui.getAmount()-amount);
           inforepo.save(ui);
           userTransactions ut=new userTransactions();
           ut.setAccountno(accountno);
           ut.setAmount(amount);
           ut.setDate(LocalDateTime.now());
           ut.setTransactiontype("Transfer");
                
           transactionClient.addTransaction(ut);
        }
         
    }

    public  List<userTransactions>  getallTransactions(int accountno) {
   return     transactionClient.getalltransactions(accountno);
    }
}