package com.bpass.backend.configs;

import com.bpass.backend.security.model.dto.SignUpAdminDto;
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
    }
}
