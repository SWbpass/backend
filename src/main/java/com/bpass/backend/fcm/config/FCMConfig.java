package com.bpass.backend.fcm.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FCMConfig {

  @PostConstruct
  public void initFCM() throws IOException {
    InputStream serviceAccount =
        new ClassPathResource("firebase/bpass-a9739-firebase-adminsdk-363pr-41d015cfe6.json").getInputStream();

    FirebaseApp firebaseApp = null;
    List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
    if(firebaseApps!=null && !firebaseApps.isEmpty()){
      for(FirebaseApp app : firebaseApps){
        if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
          firebaseApp = app;
      }
    }
    else{

      FirebaseOptions options = new FirebaseOptions.Builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .build();

      firebaseApp = FirebaseApp.initializeApp(options);
    }
  }
}
