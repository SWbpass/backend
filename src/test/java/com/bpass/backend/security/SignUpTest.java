package com.bpass.backend.security;

import com.bpass.backend.api.common.BaseControllerTest;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원가입 테스트")
class SignUpTest extends BaseControllerTest {

    @Test
    @DisplayName("회원 가입하기(성공)")
    void signUpSuccess() throws Exception {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .id("TestUser1")
                .name("테스트 유저 1")
                .password("Password")
                .phoneNumber("전화번호~")
                .address("집주소")
                .role(UserRole.ROLE_USER)
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("signup"));
    }

    @Test
    @DisplayName("회원 가입하기-매장(성공)")
    void signUpStoreSuccess() throws Exception {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .id("TestUser1")
                .name("테스트 유저 1")
                .password("Password")
                .phoneNumber("전화번호~")
                .address("집주소")
                .role(UserRole.ROLE_USER)
                .storeName("매장이름")
                .storePhoneNumber("매장번호")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("signup-store"));
    }

    @Test
    @DisplayName("아이디 중복 조회하기(사용 가능할 때)")
    void idCheckSuccess() throws Exception {
        this.mockMvc
                .perform(RestDocumentationRequestBuilders.get("/auth/checkid/{userId}", "TestUser1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("idcheck"));
    }

    @Test
    @DisplayName("아이디 중복 조회하기(사용 불가능할 때)")
    void idCheckFailBecauseExists() throws Exception {
        this.accountFactory.generateUser(1);

        this.mockMvc
                .perform(RestDocumentationRequestBuilders.get("/auth/checkid/{userId}", "TestUser1"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andDo(document("0001"));
    }
}