package com.bpass.backend.api.visit;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.api.user.model.Store;
import com.bpass.backend.api.visit.request.EntryRequest;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.dto.SignUpDto;
import com.bpass.backend.security.request.SignUpRequest;
import com.bpass.backend.testfactory.AccountFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("입장 기록 테스트")
class EntryStoreTest extends BaseControllerTest {

    @Test
    @DisplayName("입장 기록(성공)")
    void EntryStoreSuccess() throws Exception {

        SignUpDto store = this.accountFactory.generateStore(1);
        Long userId = this.accountFactory.generateUser(2).getId();

        EntryRequest entryRequest = EntryRequest.builder()
                .storeId("TestStore1")
                .visitorId("TestUser2")
                .entryTime(LocalDateTime.now())
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/visits")
                .header("Authorization", "Bearer " + store.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(entryRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("entry"));
    }
}