package com.bpass.backend.testfactory;

import com.bpass.backend.api.user.exception.InvalidStoreException;
import com.bpass.backend.api.user.exception.InvalidUserException;
import com.bpass.backend.api.user.model.StoreRepository;
import com.bpass.backend.api.user.model.UsersRepository;
import com.bpass.backend.api.visit.model.Visits;
import com.bpass.backend.api.visit.model.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class VisitFactory {

  @Autowired
  protected VisitsRepository visitsRepository;
  @Autowired
  protected UsersRepository usersRepository;
  @Autowired
  protected StoreRepository storeRepository;

  public Visits generateVisit(long visitorId, long storeId) {
    Visits visits =  visitsRepository.save(new Visits(
            usersRepository.findById(visitorId).orElseThrow(InvalidUserException::new),
            storeRepository.findById(storeId).orElseThrow(InvalidStoreException::new),
            LocalDateTime.now().minusHours(2)
    ));
    visits.setExitTime(LocalDateTime.now());
    return visitsRepository.save(visits);
  }
}
