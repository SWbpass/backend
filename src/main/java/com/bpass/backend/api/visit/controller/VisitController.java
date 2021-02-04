package com.bpass.backend.api.visit.controller;

import com.bpass.backend.api.visit.request.EntryRequest;
import com.bpass.backend.api.visit.request.ExitRequest;
import com.bpass.backend.api.visit.response.SendPushResponse;
import com.bpass.backend.api.visit.response.VisitLogsResponse;
import com.bpass.backend.api.visit.service.VisitService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public VisitLogsResponse getVisitLogs(@PathVariable Long storeId) {
        return new VisitLogsResponse(visitService.getVisitsLogs(storeId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VisitLogsResponse getAdminVisitLogs(){
        return new VisitLogsResponse(visitService.getAdminVisitsLogs());
    }

    @PostMapping("/{visitId}")
    @ResponseStatus(HttpStatus.OK)
    public SendPushResponse sendPushMessage(@PathVariable Long visitId) throws FirebaseMessagingException {
        return new SendPushResponse(visitService.sendPushMessages(visitId));
    }
}
