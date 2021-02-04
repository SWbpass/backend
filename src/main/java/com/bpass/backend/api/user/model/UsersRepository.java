package com.bpass.backend.api.user.model;

import com.bpass.backend.api.user.model.dto.UserIdDto;
import com.bpass.backend.security.model.Account;
import com.bpass.backend.security.model.UserStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

  <T> Optional<T> findByUserIdAndState(String userId, UserStatus state, Class<T> dataType);

  Optional<UserIdDto> findByUserIdAndStateIsNot(String userId, UserStatus state);
}
