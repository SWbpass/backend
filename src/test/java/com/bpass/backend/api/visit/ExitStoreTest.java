package com.bpass.backend.api.visit;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.api.visit.model.Visits;
import com.bpass.backend.api.visit.request.EntryRequest;
import com.bpass.backend.api.visit.request.ExitRequest;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.dto.SignUpDto;
import com.bpass.backend.security.request.SignUpRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("퇴장 기록 테스트")
class ExitStoreTest extends BaseControllerTest {

    @Test
    @DisplayName("퇴장 기록(성공)")
    void ExitStoreSuccess() throws Exception {

        SignUpDto store = this.accountFactory.generateStore(1);
        Long userId = this.accountFactory.generateUser(2).getId();

        Visits visit = this.visitFactory.generateVisit(userId,store.getId());

        ExitRequest exitRequest = ExitRequest.builder()
                .exitTime(LocalDateTime.now())
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/visits/{visitId}",visit.getId())
                .header("Authorization", "Bearer " + store.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(exitRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("exit"));
    }
}