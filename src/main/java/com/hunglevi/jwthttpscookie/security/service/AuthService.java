package com.hunglevi.jwthttpscookie.security.service;

import com.hunglevi.jwthttpscookie.dto.request.MessengerRes;
import com.hunglevi.jwthttpscookie.entities.Role;
import com.hunglevi.jwthttpscookie.repository.RoleRepository;
import com.hunglevi.jwthttpscookie.repository.UserRepository;
import com.hunglevi.jwthttpscookie.security.JwtUtils;
import com.hunglevi.jwthttpscookie.security.reqres.AuthReq;
import com.hunglevi.jwthttpscookie.security.reqres.RegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public MessengerRes register(RegisterReq registrationRequest) {
        return null;
    }

    @Override
    public MessengerRes login(AuthReq loginRequest) {
        MessengerRes response = new MessengerRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            var user = userRepository.findByUsername(loginRequest.getUsername());
            var jwt = jwtUtils.generateToken(user);
            Set<Role> roles = user.getRoles();
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRoles(roles);
            response.setExpirationTime("30M");
            response.setMessage("Successfully Logged In");


        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public MessengerRes refreshToken(AuthReq refreshTokenReqiest) {
        return null;
    }

    @Override
    public MessengerRes logout(AuthReq logoutRequest) {
        return null;
    }
}
