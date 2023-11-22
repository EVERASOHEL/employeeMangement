package com.employeeManagement.filter;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        String requestURI = request.getRequestURI();

        String token = extractToken(request);

        if(isValidate(token)){
            filterChain.doFilter(request,response);
        }else{
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("you do not access to this resources");
        }

    }

    private String extractToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    private boolean isValidate(String token){
        return "SPEC_INDIA".equals(token);
    }
}
