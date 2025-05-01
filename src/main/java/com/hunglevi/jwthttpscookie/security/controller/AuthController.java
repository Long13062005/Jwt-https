package com.hunglevi.jwthttpscookie.security.controller;

import com.hunglevi.jwthttpscookie.dto.request.MessengerRes;
import com.hunglevi.jwthttpscookie.security.reqres.AuthReq;
import com.hunglevi.jwthttpscookie.security.reqres.RegisterReq;
import com.hunglevi.jwthttpscookie.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessengerRes> register(@RequestBody RegisterReq reg) {
        return ResponseEntity.ok(authService.register(reg));
    }

    @PostMapping("/login")
    public ResponseEntity<MessengerRes> login(@RequestBody AuthReq req) {
        return ResponseEntity.ok(authService.login(req));
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