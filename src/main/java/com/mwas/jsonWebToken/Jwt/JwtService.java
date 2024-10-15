package com.mwas.jsonWebToken.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String SECRETKEY="81CF9DDE6D77C0F86D4B068C92404D11FA27C94B700348B51A4D9F024C3EDAB35DDE4ACEF63A260DB8FDB96B772F49A72F5824CF06F82B7306FBDACD6AB2221B";
    public static final long VALIDITY= TimeUnit.MINUTES.toMillis(30);
    public String generateToken(UserDetails userDetails){
        Map<String,String> claims =new HashMap<>();
        claims.put("iss","mwass.io");
        return  Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generateKey())
                .claims(claims)
                .compact();
}
    private SecretKey generateKey(){
        byte[] decodedKey=Base64.getDecoder().decode(SECRETKEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }


}
