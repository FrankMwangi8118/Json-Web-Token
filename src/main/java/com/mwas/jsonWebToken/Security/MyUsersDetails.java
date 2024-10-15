package com.mwas.jsonWebToken.Security;

import com.mwas.jsonWebToken.Models.MyUsers;
import com.mwas.jsonWebToken.Repository.MyUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUsersDetails implements UserDetailsService {
    @Autowired
    private MyUsersRepository myUsersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUsers> myUsers=myUsersRepository.findByUsername(username);
        if(myUsers.isEmpty()) {
            throw new UsernameNotFoundException("wrong Username or password");
        }else{
            var userObj=myUsers.get();
            return User.builder()
                    .password(userObj.getPassword())
                    .username(userObj.getUsername())
                    .roles(getRoles(userObj))
                    .build();
    }}
    private String[] getRoles(MyUsers userObj) {
        if(userObj.getRoles()==null){
            return new String[]{"USER"};
        }else return userObj.getRoles().split(",");
    }
}
