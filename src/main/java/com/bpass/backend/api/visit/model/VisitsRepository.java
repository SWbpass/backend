package com.bpass.backend.api.visit.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitsRepository extends JpaRepository<Visits, Long> {
    Optional<Visits> findByVisitor_IdAndStore_IdAndEntryTime(Long visitorId, Long storeId, LocalDateTime entryTime);

    List<Visits> findAllByStore_Id(Long storeId);

    List<Visits> findAllByStore_IdAndEntryTimeBetweenAndExitTimeBetween(Long storeId,LocalDateTime startDate, LocalDateTime endDate,LocalDateTime startDate2, LocalDateTime endDate2);
}
