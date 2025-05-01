package com.hunglevi.jwthttpscookie.security.service;

import com.hunglevi.jwthttpscookie.dto.request.MessengerRes;
import com.hunglevi.jwthttpscookie.security.reqres.AuthReq;
import com.hunglevi.jwthttpscookie.security.reqres.RegisterReq;

public interface IAuthService {

    MessengerRes register(RegisterReq registrationRequest);

    MessengerRes login(AuthReq loginRequest);

    MessengerRes refreshToken(AuthReq refreshTokenReqiest);


    MessengerRes logout(AuthReq logoutRequest);

}
