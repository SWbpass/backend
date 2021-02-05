package com.bpass.backend.api.visit.service;

import com.bpass.backend.api.user.exception.InvalidStoreException;
import com.bpass.backend.api.user.exception.InvalidUserException;
import com.bpass.backend.api.user.model.StoreRepository;
import com.bpass.backend.api.user.model.UsersRepository;
import com.bpass.backend.api.visit.exception.VisitsNotExistsException;
import com.bpass.backend.api.visit.model.Visits;
import com.bpass.backend.api.visit.model.VisitsRepository;
import com.bpass.backend.api.visit.model.dto.VisitsDto;
import com.bpass.backend.fcm.service.FcmService;
import com.bpass.backend.fcm.model.dto.PushContentsDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final UsersRepository usersRepository;
    private final StoreRepository storeRepository;
    private final VisitsRepository visitsRepository;
    private final FcmService fcmService;

    @Transactional
    public long entryStore(Long visitorId, Long storeId, LocalDateTime time) {
        return visitsRepository.save(
                new Visits(
                        usersRepository.findById(visitorId).orElseThrow(InvalidUserException::new),
                        storeRepository.findById(storeId).orElseThrow(InvalidStoreException::new),
                        time
                )
        ).getId();
    }

    @Transactional
    public void exitStore(Long visitId, LocalDateTime exitTime) {
        Visits visits = visitsRepository.findById(visitId)
                .orElseThrow(VisitsNotExistsException::new);
        visits.setExitTime(exitTime);
    }

    public List<VisitsDto> getVisitsLogs(Long storeId) {
        return visitsRepository.findAllByStore_Id(storeId).stream().map(VisitsDto::new).collect(Collectors.toList());
    }

    public List<VisitsDto> getAdminVisitsLogs(String storeName, String visitorName, LocalDateTime time) {
        List<VisitsDto> visits =  visitsRepository.findAll().stream().map(VisitsDto::new).collect(Collectors.toList());
        List<VisitsDto> result =visits;

        if(storeName!=null)
            result = visits.stream().filter(visitsDto -> visitsDto.getStore().getStoreName().equals(storeName)).collect(Collectors.toList());
        if(visitorName != null)
            result = result.stream().filter(visitsDto -> visitsDto.getVisitor().getName().equals(visitorName)).collect(Collectors.toList());
        if(time != null)
            result = result.stream().filter(visitsDto -> checkTime(visitsDto,time)).collect(Collectors.toList());
        return result;

    }

    private Boolean checkTime(VisitsDto visitsDto, LocalDateTime time){
        return time.isBefore(visitsDto.getExitTime()) && time.isAfter(visitsDto.getEntryTime());
    }

    public int sendPushMessages(Long visitId) throws FirebaseMessagingException {
        Visits visit = visitsRepository.findById(visitId).orElseThrow(VisitsNotExistsException::new);
        List<String> visitors = visitsRepository.findAllByStore_IdAndEntryTimeBetweenAndExitTimeBetween(visit.getStore().getId(), visit.getEntryTime(), visit.getExitTime(), visit.getEntryTime(), visit.getExitTime())
                .stream().map(visits -> visits.getVisitor().getUserId()).collect(Collectors.toList());
        return fcmService.sendPushMessages(new PushContentsDto(visit), visitors).getSuccessCount();
    }

}
