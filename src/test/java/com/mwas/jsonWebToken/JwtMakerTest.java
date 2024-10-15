package com.mwas.jsonWebToken;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtMakerTest {
    @Test
    public void generateSecretKey(){
       SecretKey key= Jwts.SIG.HS512.key().build();
       String encodedKey=DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(encodedKey);
    }
}
