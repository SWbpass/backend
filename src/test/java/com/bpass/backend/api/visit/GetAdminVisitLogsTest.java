package com.bpass.backend.api.visit;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.api.visit.request.ExitRequest;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("관리자용 방문자 조회 테스트")
class GetAdminVisitLogsTest extends BaseControllerTest {

    @Test
    @DisplayName("관리자용 방문자 조회(성공)")
    void GetAdminVisitLogsSuccess() throws Exception {

        Long storeId1 = this.accountFactory.generateStore(1).getId();
        Long storeId2 = this.accountFactory.generateStore(2).getId();
        Long userId1 = this.accountFactory.generateUser(3).getId();
        Long userId2 = this.accountFactory.generateUser(4).getId();

        String adminAccessToken = this.accountFactory.generateAdmin(5).getAccessToken();

        this.visitFactory.generateVisit(userId1,storeId1);
        this.visitFactory.generateVisit(userId1,storeId2);
        this.visitFactory.generateVisit(userId2,storeId1);
        this.visitFactory.generateVisit(userId2,storeId2);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/visits")
                .header("Authorization", "Bearer " + adminAccessToken)
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("admin-visit"));
    }
}