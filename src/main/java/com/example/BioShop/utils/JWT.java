package com.example.BioShop.utils;

import com.example.BioShop.entities.MyUser;
import com.example.BioShop.repositories.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlEnumValue;
import java.util.*;
import java.util.function.Function;

@Service
public class JWT {

    @Autowired
    private UserRepository userRepository;

    private String secret;
    private int jwtExpirationInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret){
        this.secret = secret;
    }

    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs){
       this.jwtExpirationInMs = jwtExpirationInMs;
    }

   public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
       Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
       if(roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
           claims.put("isAdmin", true);
       }
       if(roles.contains(new SimpleGrantedAuthority("ROLE_USER"))){
           claims.put("isUser", true);
       }
       return doGenerateToken(claims, userDetails.getUsername());
   }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String authToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new BadCredentialsException("Invalid Credentials", e);
        }
        catch (ExpiredJwtException e){
            throw e;
        }
    }

    public String getUsernameFromToken(String token){
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        MyUser myUser = userRepository.findByUsername(claims.getSubject());
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles = Arrays.asList(new SimpleGrantedAuthority(myUser.getRole()));
        return roles;
    }

}
