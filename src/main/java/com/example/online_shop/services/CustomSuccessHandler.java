package com.example.online_shop.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(r -> r.getAuthority()).findAny();

        switch (roles.orElse("")) {
            case "ADMIN" -> response.sendRedirect("/admin");
            case "USER" -> response.sendRedirect("/");
            case "SELLER" -> response.sendRedirect("/seller");
            default -> response.sendRedirect("/error");
        }
    }
}
