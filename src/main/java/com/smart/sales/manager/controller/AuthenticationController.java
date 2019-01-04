 package com.smart.sales.manager.controller;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.smart.sales.manager.config.TokenProvider;

import com.smart.sales.manager.request.model.LoginUser;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.response.model.AuthToken;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> login(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("login.success", null, locale),new AuthToken(token));
        //return new ApiResponse<>(200, "Success", new AuthToken(token));
        
    }

}
