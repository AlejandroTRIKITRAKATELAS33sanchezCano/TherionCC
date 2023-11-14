package com.therioncc.therion.services;

import com.therioncc.therion.models.Duenno;
import com.therioncc.therion.repositories.IDuennoRepositorio;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    private final IDuennoRepositorio iDuennoRepositorio;

    public static final String SECRET_KEY ="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public JwtService(IDuennoRepositorio iDuennoRepositorio) {
        this.iDuennoRepositorio = iDuennoRepositorio;
    }

    public String getToken(UserDetails userDetails) {
        String token = getToken(new HashMap<>(), userDetails);
        System.out.println("Valor del token: " + token);
        return token;
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails){


        String token = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        System.out.println(token);
        return token;
    }

    public Duenno getDuennoFromToken(String token) {
        String username = getUsernameFromToken(token);
        return iDuennoRepositorio.findByDuCorreo(username).orElseThrow();
    }

    private Key getKey() {
        byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllCalimas(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllCalimas(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
