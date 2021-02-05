package com.bpass.backend.api.fcm;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.fcm.request.RegisterTokenRequest;
import com.bpass.backend.security.model.dto.SignUpDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("퇴장 기록 테스트")
class RegisterTokenTest extends BaseControllerTest {

    @Test
    @DisplayName("FCM토큰 등록 (성공)")
    void RegisterTokenSuccess() throws Exception {

        SignUpDto user = this.accountFactory.generateUser(1);

        RegisterTokenRequest registerTokenRequest = RegisterTokenRequest.builder()
                .userId("TestUser" + 1)
                .token("Token Value")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/push")
                .header("Authorization", "Bearer " + user.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(registerTokenRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("registerToken"));
    }
}