package com.example.CardUserDetails.Controller;
import com.example.CardUserDetails.Model.AmountTransfer;
import com.example.CardUserDetails.Model.SigningDetails;
import com.example.CardUserDetails.Model.UserInfo;
import com.example.CardUserDetails.Model.userTransactions;
import com.example.CardUserDetails.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useraccount")
public class UserDetailsController {
    @Autowired
    Service service;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SigningDetails sud){
        if(service.validDetails(sud.getUsername())){
            service.signUpUser(sud);
            return new ResponseEntity<>("Signup Succedssfull", HttpStatus.OK);

        }
        return new ResponseEntity<>("Username already Exists", HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("/login")
    public ResponseEntity<UserInfo> signin(@RequestBody SigningDetails sud,HttpSession session){
        UserInfo ui=new UserInfo();
        if (service.loginVerification(sud)){
            ui=service.getUserInfo(sud);
              session.setAttribute("user",sud.getUsername());
              session.setAttribute("accountno",ui.getCardno());
        }return new ResponseEntity<>(ui,HttpStatus.OK);
    }
    @PostMapping("/withdraw/{amount}")
    public ResponseEntity<UserInfo> withdraw(   @PathVariable("amount") int amount,HttpSession session){

            SigningDetails sid=new SigningDetails();
            sid.setUsername((String) session.getAttribute("user"));
        service.withDraw(amount, (String) session.getAttribute("user"),(int)session.getAttribute("accountno"));
        return new ResponseEntity<>(service.getUserInfo(sid),HttpStatus.OK);

    }
    @PostMapping("/deposit/{amount}")
    public ResponseEntity<UserInfo> add(@PathVariable("amount")int money,HttpSession session){
        SigningDetails sid=new SigningDetails();
        sid.setUsername((String) session.getAttribute("user"));
        service.depositMoney(money,(String)session.getAttribute("user"),(int)session.getAttribute("accountno"));
        return new ResponseEntity<>(service.getUserInfo(sid),HttpStatus.OK);
    }
    @PostMapping("/transfer/{amount}/{accountno}")
    public ResponseEntity<UserInfo> transfer(@PathVariable("amount")int money,@PathVariable("accountno")int taccountno,HttpSession session){
 SigningDetails sid=new SigningDetails();
        sid.setUsername((String) session.getAttribute("user"));
        service.transferMoney(money,(String)session.getAttribute("user"),(int)session.getAttribute("accountno"),  taccountno);
       return new ResponseEntity<>(service.getUserInfo(sid),HttpStatus.OK);
    }

    @GetMapping("/gettransactionhistory")
    public ResponseEntity<List<userTransactions>> transactionHistory( HttpSession session){

     return new ResponseEntity<>(  service.getallTransactions((int)session.getAttribute("accountno")),HttpStatus.OK);
    }
    @PostMapping("/login/savingsinterestdetails")
    public void interestDetails(@RequestBody SigningDetails sud){

    }
}
