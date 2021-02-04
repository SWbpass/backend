package com.bpass.backend.api.user.service;

import com.bpass.backend.api.user.model.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 사용자 서비스.
 *
 * @author always0ne
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final UsersRepository usersRepository;

}
