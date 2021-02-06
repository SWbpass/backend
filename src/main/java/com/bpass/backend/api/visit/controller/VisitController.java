package com.bpass.backend.api.visit.controller;

import com.bpass.backend.api.visit.request.EntryRequest;
import com.bpass.backend.api.visit.request.ExitRequest;
import com.bpass.backend.api.visit.response.GetSuspiciousResponse;
import com.bpass.backend.api.visit.response.SendPushResponse;
import com.bpass.backend.api.visit.response.VisitLogsResponse;
import com.bpass.backend.api.visit.service.VisitService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/visits")
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public long entry(@RequestBody EntryRequest entryRequest) {
        return visitService.entryStore(entryRequest.getVisitorId(), entryRequest.getStoreId(), entryRequest.getEntryTime());
    }

    @PutMapping("/{visitId}")
    @ResponseStatus(HttpStatus.OK)
    public void exitStore(@RequestBody ExitRequest exitRequest, @PathVariable Long visitId) {
        visitService.exitStore(visitId, exitRequest.getExitTime());
    }

    @GetMapping("/{storeId}")
    @ResponseStatus(HttpStatus.OK)
    public VisitLogsResponse getVisitLogs(@PathVariable String storeId) {
        return new VisitLogsResponse(visitService.getVisitsLogs(storeId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VisitLogsResponse getAdminVisitLogs(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String visitorName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time
    ){
        return new VisitLogsResponse(visitService.getAdminVisitsLogs(storeName,visitorName,time));
    }

    @PostMapping("/{visitId}")
    @ResponseStatus(HttpStatus.OK)
    public SendPushResponse sendPushMessage(@PathVariable Long visitId) throws FirebaseMessagingException {
        return new SendPushResponse(visitService.sendPushMessages(visitId));
    }

    @GetMapping("/suspicious/{visitId}")
    @ResponseStatus(HttpStatus.OK)
    public GetSuspiciousResponse getSuspicious(@PathVariable Long visitId){
        return new GetSuspiciousResponse(visitService.getSuspicious(visitId));
    }
}
