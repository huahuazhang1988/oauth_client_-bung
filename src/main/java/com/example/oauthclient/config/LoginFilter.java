package com.example.oauthclient.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginFilter  extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if(!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("authentication method not allowed");
        }
        if(request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            Map<String,String> map = null;
            try {
                map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String username = map.get(getUsernameParameter());
            String password = map.get(getPasswordParameter());
            System.out.println("username is "+ username+" password is "+password);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
            setDetails(request,token);
            return this.getAuthenticationManager().authenticate(token);

        }
        return super.attemptAuthentication(request,response);

    }
}
