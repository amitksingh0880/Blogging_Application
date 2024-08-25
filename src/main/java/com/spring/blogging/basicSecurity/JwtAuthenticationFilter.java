package com.spring.blogging.basicSecurity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization"); // Gets the Token
        System.out.println("Token: "+requestToken);

        String userName = null;
        String token = null;   // Bearer 983745987y9sjhb


        if(requestToken != null && requestToken.startsWith("Basic")) {
            token = requestToken.substring(7);
            try {
                userName = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt Token");
            } catch (ExpiredJwtException e) {
                System.out.println("Token Expired!");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid Jwt token!");
            }
        } else {
            System.out.println("JWT token does not begin with Bearer!..");
        }


        //Once we Get the token We validate the Token

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
           UserDetails userDetails =  this.userDetailsService.loadUserByUsername(userName);
                  if(this.jwtTokenHelper.validateToken(token , userDetails ))
                  {
                      //Authetication Karna hai...
                      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                      usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                  }else{
                      System.out.println("Invalid jwt Token!..");
                  }
        }else{
            System.out.println("Username is null or context is null!");
        }

        filterChain.doFilter(request , response);
    }
}
