package com.bpass.backend.fcm.model;

import com.bpass.backend.api.visit.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FcmTokensRepository extends JpaRepository<FcmTokens, Long> {
    List<FcmTokens> findAllByUserIdIn(List<String> userIds);
}
