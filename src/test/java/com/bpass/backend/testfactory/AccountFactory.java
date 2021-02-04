package com.bpass.backend.testfactory;

import com.bpass.backend.api.user.model.StoreRepository;
import com.bpass.backend.api.user.model.Users;
import com.bpass.backend.api.user.model.UsersRepository;
import com.bpass.backend.security.model.Account;
import com.bpass.backend.security.model.UserStatus;
import com.bpass.backend.security.model.dto.SignUpAdminDto;
import com.bpass.backend.security.model.dto.SignUpDto;
import com.bpass.backend.security.model.dto.SignUpStoreDto;
import com.bpass.backend.security.model.dto.SignUpUserDto;
import com.bpass.backend.security.response.SignInResponse;
import com.bpass.backend.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountFactory {

  @Autowired
  private AuthService authService;
  @Autowired
  protected UsersRepository usersRepository;
  @Autowired
  protected StoreRepository storeRepository;

  @Transactional
  public SignUpDto generateUser(int index) {
    SignInResponse response = authService.signUpUser(
            new SignUpUserDto(
                    "TestUser" + index,
                    "password",
                    "테스트 유저 " + index,
                    "010-1234-5678",
                    "주소"
            )
    );
    return new SignUpDto(response.getAccessToken(),usersRepository.findByUserIdAndState("TestUser" + index, UserStatus.NORMAL, Account.class)
            .get().getId());
  }

  @Transactional
  public SignUpDto generateAdmin(int index) {
    SignInResponse response = authService.signUpAdmin(
            new SignUpAdminDto(
                    "TestUser" + index,
                    "password",
                    "테스트 유저 " + index,
                    "010-1234-5678",
                    "주소"
            )
    );
    return new SignUpDto(response.getAccessToken(),usersRepository.findByUserIdAndState("TestUser" + index, UserStatus.NORMAL, Account.class)
            .get().getId());
  }

  @Transactional
  public SignUpDto generateStore(int index) {
    SignInResponse response = authService.signUpStore(
            new SignUpStoreDto(
                    "TestUser" + index,
                    "password",
                    "테스트 유저 " + index,
                    "010-1234-5678",
                    "주소",
                    "010-1234-5678",
                    "매장명" + index,
                    "위도값",
                    "경도값"
            )
    );
    return new SignUpDto(response.getAccessToken(),storeRepository.findByUserIdAndState("TestUser" + index, UserStatus.NORMAL, Account.class)
            .get().getId());
  }
}
