package com.mwas.jsonWebToken.Controllers;

import com.mwas.jsonWebToken.Models.MyUsers;
import com.mwas.jsonWebToken.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyUserController {
    @Autowired
    private MyUserService myUserService;
public MyUsers Signup(@RequestBody MyUsers myUsers){
    return myUserService.register(myUsers);
}
}
