package com.bpass.backend.api.visit;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.dto.SignUpDto;
import com.bpass.backend.security.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("방문자 조회 테스트")
class GetVisitLogsTest extends BaseControllerTest {

    @Test
    @DisplayName("방문자 조회(성공)")
    void GetVisitLogsSuccess() throws Exception {

        SignUpDto store = this.accountFactory.generateStore(1);
        Long storeId2 = this.accountFactory.generateStore(2).getId();
        Long userId1 = this.accountFactory.generateUser(3).getId();
        Long userId2 = this.accountFactory.generateUser(4).getId();

        this.visitFactory.generateVisit(userId1,store.getId());
        this.visitFactory.generateVisit(userId1,storeId2);
        this.visitFactory.generateVisit(userId2,store.getId());
        this.visitFactory.generateVisit(userId2,storeId2);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/visits/{storeId}", store.getId())
                .header("Authorization", "Bearer " + store.getAccessToken())
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("visit"));
    }
}