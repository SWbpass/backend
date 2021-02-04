package com.bpass.backend.api.visit;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.api.visit.model.Visits;
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

@DisplayName("동선 겹친사람 푸시 테스트")
class SendPushMessageTest extends BaseControllerTest {


    @Test
    @Disabled
    @DisplayName("푸시(성공)")
    void PushSuccess() throws Exception {

        SignUpDto store = this.accountFactory.generateStore(1);
        Long userId = this.accountFactory.generateUser(2).getId();

        Visits visit = this.visitFactory.generateVisit(userId,store.getId());


        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/visits/{visitId}",visit.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("push"));
    }
}