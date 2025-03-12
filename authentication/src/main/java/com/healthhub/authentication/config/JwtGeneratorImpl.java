package com.healthhub.authentication.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.healthhub.authentication.model.User;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken= Jwts.builder()
        .setSubject((user.getUserName()))
        .setClaims(Map.of("role", user.getRole()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + expirationTime))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();

        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }

    private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }
}
