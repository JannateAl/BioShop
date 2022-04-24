package com.example.BioShop.filter;

import com.example.BioShop.Security.MyUserDetailsService;
import com.example.BioShop.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailService;

    @Autowired
    private JWT jwtutil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

       String jwtToken = extractJwtFromRequest(request);
       if(StringUtils.hasText(jwtToken) && jwtutil.validateToken(jwtToken)){
           UserDetails userDetails = new User(jwtutil.getUsernameFromToken(jwtToken),
                   "", jwtutil.getRolesFromToken(jwtToken));
           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                   new UsernamePasswordAuthenticationToken
                           (userDetails, "", jwtutil.getRolesFromToken(jwtToken));
           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
       }
       else{
           System.out.println("Security context cannot be set");
       }
        chain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
