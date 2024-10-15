package com.mwas.jsonWebToken.Services;

import com.mwas.jsonWebToken.Models.MyUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
@Autowired
private PasswordEncoder passwordEncoder;
    public MyUsers register(MyUsers myUsers) {
        myUsers.setPassword(passwordEncoder.encode(myUsers.getPassword()));
        return myUsers;
    }
}
