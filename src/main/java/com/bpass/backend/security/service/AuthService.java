package com.bpass.backend.security.service;

import com.bpass.backend.api.user.model.Store;
import com.bpass.backend.api.user.model.StoreRepository;
import com.bpass.backend.api.user.model.Users;
import com.bpass.backend.api.user.model.UsersRepository;
import com.bpass.backend.security.JwtTokenProvider;
import com.bpass.backend.security.exception.CantSignInException;
import com.bpass.backend.security.exception.IdAlreadyExistsException;
import com.bpass.backend.security.model.Account;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.UserStatus;
import com.bpass.backend.security.model.dto.SignUpAdminDto;
import com.bpass.backend.security.model.dto.SignUpStoreDto;
import com.bpass.backend.security.model.dto.SignUpUserDto;
import com.bpass.backend.security.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final StoreRepository storeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignInResponse signIn(String id, String password) {
        Account account =
                this.usersRepository.findByUserIdAndState(id, UserStatus.NORMAL, Account.class)
                        .orElseThrow(() -> new CantSignInException(id));
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new CantSignInException(id);
        }
        return generateSignInResponse(account);
    }

    @Transactional
    public SignInResponse signInStore(String id, String password) {
        Account account =
                this.storeRepository.findByUserIdAndState(id, UserStatus.NORMAL, Account.class)
                        .orElseThrow(() -> new CantSignInException(id));
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new CantSignInException(id);
        }
        return generateSignInResponse(account);
    }

    @Transactional
    public SignInResponse signUpUser(SignUpUserDto signUpUserDto) {
        Account account = this.usersRepository.save(
                new Users(
                        signUpUserDto.getId(),
                        passwordEncoder.encode(signUpUserDto.getPassword()),
                        signUpUserDto.getName(),
                        UserStatus.NORMAL,
                        signUpUserDto.getPhoneNumber(),
                        signUpUserDto.getAddress(),
                        Collections.singletonList(UserRole.ROLE_USER)
                ));

        return generateSignInResponse(account);
    }

    @Transactional
    public SignInResponse signUpStore(SignUpStoreDto signUpStoreDto) {
        Account account = this.storeRepository.save(
                new Store(
                        signUpStoreDto.getId(),
                        passwordEncoder.encode(signUpStoreDto.getPassword()),
                        signUpStoreDto.getName(),
                        UserStatus.NORMAL,
                        signUpStoreDto.getPhoneNumber(),
                        signUpStoreDto.getAddress(),
                        signUpStoreDto.getStorePhoneNumber(),
                        signUpStoreDto.getStoreName(),
                        Collections.singletonList(UserRole.ROLE_STORE),
                        signUpStoreDto.getLatitude(),
                        signUpStoreDto.getLongitude()
                ));

        return generateSignInResponse(account);
    }

    @Transactional
    public SignInResponse signUpAdmin(SignUpAdminDto signUpAdminDto) {
        Account account = this.usersRepository.save(
                new Users(
                        signUpAdminDto.getId(),
                        passwordEncoder.encode(signUpAdminDto.getPassword()),
                        signUpAdminDto.getName(),
                        UserStatus.NORMAL,
                        signUpAdminDto.getPhoneNumber(),
                        signUpAdminDto.getAddress(),
                        Collections.singletonList(UserRole.ROLE_ADMIN)
                ));

        return generateSignInResponse(account);
    }

    private SignInResponse generateSignInResponse(Account account) {
        return SignInResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(account.getUserId(), account.getRoles()))
                .build();
    }

    @Transactional(readOnly = true)
    public void idCheck(String id) {
        if (this.usersRepository.findByUserIdAndStateIsNot(id, UserStatus.WITHDRAWAL).isPresent()) {
            throw new IdAlreadyExistsException(id);
        }
        if (this.storeRepository.findByUserIdAndStateIsNot(id, UserStatus.WITHDRAWAL).isPresent()) {
            throw new IdAlreadyExistsException(id);
        }
    }
}
