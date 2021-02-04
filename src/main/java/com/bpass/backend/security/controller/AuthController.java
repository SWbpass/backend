package com.bpass.backend.security.controller;

import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.request.SignInRequest;
import com.bpass.backend.security.request.SignUpRequest;
import com.bpass.backend.security.response.SignInResponse;
import com.bpass.backend.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(
            @RequestBody SignInRequest signInRequest
    ) {
        if (signInRequest.getRole() != UserRole.ROLE_STORE)
            return this.authService.signIn(signInRequest.getId(), signInRequest.getPassword());
        else
            return this.authService.signInStore(signInRequest.getId(), signInRequest.getPassword());
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signUp(
            @RequestBody SignUpRequest signUpRequest
    ) {
        if (signUpRequest.getRole() == UserRole.ROLE_USER)
            return this.authService.signUpUser(signUpRequest.toUserDto());
        else if (signUpRequest.getRole() == UserRole.ROLE_STORE)
            return this.authService.signUpStore(signUpRequest.toStoreDto());
        else
            return this.authService.signUpAdmin(signUpRequest.toAdminDto());
    }

    @GetMapping("/checkid/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void idCheck(
            @PathVariable String userId
    ) {
        this.authService.idCheck(userId);
    }
}
