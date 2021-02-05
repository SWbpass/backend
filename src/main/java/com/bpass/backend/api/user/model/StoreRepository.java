package com.bpass.backend.api.user.model;

import com.bpass.backend.api.user.model.dto.UserIdDto;
import com.bpass.backend.security.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
  <T> Optional<T> findByUserIdAndState(String userId, UserStatus state, Class<T> dataType);

  Optional<UserIdDto> findByUserIdAndStateIsNot(String userId, UserStatus state);


  Optional<Store> findByUserId(String userId);
}
