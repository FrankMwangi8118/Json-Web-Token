package com.mwas.jsonWebToken.Controllers;

import com.mwas.jsonWebToken.Jwt.JwtService;
import com.mwas.jsonWebToken.Models.LoginForm;
import com.mwas.jsonWebToken.Models.MyUsers;
import com.mwas.jsonWebToken.Repository.MyUsersRepository;
import com.mwas.jsonWebToken.Security.MyUsersDetails;
import com.mwas.jsonWebToken.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyUserController {
    @Autowired
    private MyUsersRepository myUsersRepository;
    @Autowired
    private MyUsersDetails myUsersDetails;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserService myUserService;
    @PostMapping("/newAccount")
public MyUsers Signup(@RequestBody MyUsers myUsers){
    return myUserService.register(myUsers);
}
@GetMapping("/user/home")
public String UserHome(){
    return "welcome User";
}
@GetMapping("/user/admin")
    public String AdminHome(){
    return "admin User";
}
@GetMapping("/")
    public String home(){
    return "welcome home";
    }
    @PostMapping("/auth")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
     Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
           loginForm.username(),loginForm.password()
     ));
     if (authentication.isAuthenticated()){
         return jwtService.generateToken(myUsersDetails.loadUserByUsername(loginForm.username()));
     }else{
         throw  new UsernameNotFoundException("no username"+loginForm.username());
     }
    }
    @GetMapping("/allUsers")
    public List<MyUsers> allUsers(){
        return myUsersRepository.findAll();
    }
}
