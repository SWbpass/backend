package com.bpass.backend.configs;

import com.bpass.backend.api.visit.service.VisitService;
import com.bpass.backend.security.model.dto.SignUpAdminDto;
import com.bpass.backend.security.model.dto.SignUpStoreDto;
import com.bpass.backend.security.model.dto.SignUpUserDto;
import com.bpass.backend.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class InitAdminUser implements ApplicationListener<ApplicationStartedEvent> {

    private final AuthService authService;
    private final VisitService visitService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        authService.signUpAdmin(
                new SignUpAdminDto("admin@bpass.com", "password", "관리자", "전화번호", "서울시 동작구")
        );

        authService.signUpStore(
                new SignUpStoreDto("test1@store.com", "password", "점장 1", "010-1234-5678", "용산 청파", "010-1234-5678", "이디야", "37.549469", "126.9694083 ")
        );
        authService.signUpStore(
                new SignUpStoreDto("test2@store.com", "password", "점장 2", "010-1234-5678", "서울역 동자동", "010-1234-5678", "스타벅스", "37.5513775", "126.9736404")
        );

        authService.signUpUser(
                new SignUpUserDto("test1@user.com","password","테스트유저 1", "010-9876-4321", "서울시 동작구")
        );

        authService.signUpUser(
                new SignUpUserDto("test2@user.com","password","테스트유저 2", "010-9876-4321", "서울시 구로구")
        );

        Long visitN = visitService.entryStore("test1@user.com","test2@store.com",LocalDateTime.now());
        visitService.exitStore(visitN, LocalDateTime.now().plusHours(2));


        Long visitN2 = visitService.entryStore("test2@user.com","test2@store.com",LocalDateTime.now().plusHours(1));
        visitService.exitStore(visitN2, LocalDateTime.now().plusHours(4));


        Long visitN3 = visitService.entryStore("admin@bpass.com","test2@store.com",LocalDateTime.now().plusHours(3));
        visitService.exitStore(visitN3, LocalDateTime.now().plusHours(4));
    }
}
