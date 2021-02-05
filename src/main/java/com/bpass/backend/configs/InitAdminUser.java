package com.bpass.backend.configs;

import com.bpass.backend.security.model.dto.SignUpAdminDto;
import com.bpass.backend.security.model.dto.SignUpStoreDto;
import com.bpass.backend.security.model.dto.SignUpUserDto;
import com.bpass.backend.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class InitAdminUser implements ApplicationListener<ApplicationStartedEvent> {

    private final AuthService authService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        authService.signUpAdmin(
                new SignUpAdminDto("admin@bpass.com", "password", "관리자", "전화번호", "집")
        );

        authService.signUpStore(
                new SignUpStoreDto("test1@store.com", "password", "테스트가게 1", "010-1234-5678", "주소", "가게번호", "테스트 가게 1", "37.549469", "126.9694083 ")
        );
        authService.signUpStore(
                new SignUpStoreDto("test2@store.com", "password", "테스트가게 2", "010-1234-5678", "주소", "가게번호", "테스트가게 2", "37.5513775", "126.9736404")
        );

        authService.signUpUser(
                new SignUpUserDto("test1@user.com","password","테스트유저 1", "010-9876-4321", "집주소")
        );

        authService.signUpUser(
                new SignUpUserDto("test2@user.com","password","테스트유저 2", "010-9876-4321", "집주소")
        );
    }
}
