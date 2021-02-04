package com.bpass.backend.fcm.controller;

import com.bpass.backend.api.visit.request.EntryRequest;
import com.bpass.backend.api.visit.request.ExitRequest;
import com.bpass.backend.api.visit.response.SendPushResponse;
import com.bpass.backend.api.visit.response.VisitLogsResponse;
import com.bpass.backend.api.visit.service.VisitService;
import com.bpass.backend.fcm.request.RegisterTokenRequest;
import com.bpass.backend.fcm.service.FcmService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/push")
public class FcmController {

    private final FcmService fcmService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void registerToken(@RequestBody RegisterTokenRequest registerTokenRequest){
        fcmService.registerToken(registerTokenRequest.getUserId(),registerTokenRequest.getToken());
    }
}
