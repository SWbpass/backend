package com.bpass.backend.fcm.service;

import com.bpass.backend.fcm.model.FcmTokens;
import com.bpass.backend.fcm.model.FcmTokensRepository;
import com.bpass.backend.fcm.model.dto.PushContentsDto;
import com.google.firebase.messaging.*;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmService {

    private final FcmTokensRepository fcmTokensRepository;

  public BatchResponse sendPushMessages(PushContentsDto pushContentsDto, List<String> users)
      throws FirebaseMessagingException {
      List<String> tokens = fcmTokensRepository.findAllByUserIdIn(users).stream().map(FcmTokens::getToken).collect(Collectors.toList());
   MulticastMessage message = MulticastMessage.builder()
        .setNotification(new Notification(pushContentsDto.getTitle(), pushContentsDto.getBody()))
        .putData("latitude", pushContentsDto.getLatitude())
        .putData("longitude", pushContentsDto.getLongitude())
        .addAllTokens(tokens)
        .build();

    return FirebaseMessaging.getInstance().sendMulticast(message);
  }

    public void registerToken(String userId, String token) {
        fcmTokensRepository.save(new FcmTokens(userId,token));
    }
}
