package com.hunglevi.jwthttpscookie.security.controller;

import com.hunglevi.jwthttpscookie.dto.request.MessengerRes;
import com.hunglevi.jwthttpscookie.security.JwtUtils;
import com.hunglevi.jwthttpscookie.security.reqres.AuthReq;
import com.hunglevi.jwthttpscookie.security.reqres.RegisterReq;
import com.hunglevi.jwthttpscookie.security.service.AuthService;
import com.hunglevi.jwthttpscookie.security.service.JpaUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JpaUserDetailsService detailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<MessengerRes> register(@RequestBody RegisterReq reg) {
        return ResponseEntity.ok(authService.register(reg));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthReq req, HttpServletResponse response) {
        try {

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword(),
                            new ArrayList<>()));
            final UserDetails user = detailsService.loadUserByUsername(req.getUsername());
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setMaxAge(60 * 30 * 1000); // expires in 30 minutes
//                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                cookie.setPath("/"); // Global
                response.addCookie(cookie);
                return ResponseEntity.ok(authService.login(req));
            }
            return ResponseEntity.status(400).body("Error authenticating");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(400).body("" + e.getMessage());
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<MessengerRes> refreshToken(@RequestBody AuthReq req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessengerRes> logout(@RequestBody AuthReq req) {
        return ResponseEntity.ok(authService.logout(req));
    }


}