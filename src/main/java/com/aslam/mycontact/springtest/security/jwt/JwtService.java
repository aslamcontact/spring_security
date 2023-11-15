package com.aslam.mycontact.springtest.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


    private  static final String SECRET_KEY="41c173246c1632ee0becd7556cb66e3d024118888e963fa1b68a19e5eff61e51";
     private Key getSignInKey()
     {
         byte[] byteKey= Decoders.BASE64.decode(SECRET_KEY);
         return Keys.hmacShaKeyFor(byteKey);

     }
    public String generateTokens(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    )
    {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwt,UserDetails userDetails)
    {
        final String userName=extractUserName(jwt);
        return (userName.equals(userDetails.getUsername())) && isTokenIsExpired(jwt);
    }

    private boolean isTokenIsExpired(String jwt)
    {
        return extractExpiration(jwt).before(new Date());
    }



    public String extractUserName(String jwt)
    {
        return extractClaim(jwt,Claims::getSubject);
    }

    private Date extractExpiration(String jwt)
    {
        return  extractClaim(jwt,Claims::getExpiration);
    }





    public Claims extractAllClaims(String jwt)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(jwt)
                .getBody();
    }

    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver)
    {
        final Claims claims=extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }


}
