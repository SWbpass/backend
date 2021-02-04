package com.bpass.backend.api.common;

import org.junit.jupiter.api.DisplayName;

@DisplayName("시스템 오류 테스트")
class SystemErrorTest extends BaseControllerTest {
/*
  @Test
  @WithMockUser("TestUser1")
  @DisplayName("맞지 않는 RequestBody가 왔을 때")
  void systemFailBecauseWrongRequest() throws Exception {
    AddCommentRequest addCommentRequest = AddCommentRequest.builder().message("wrong").build();
    this.mockMvc.perform(RestDocumentationRequestBuilders.post("/board/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(this.objectMapper.writeValueAsString(addCommentRequest)))
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andDo(document("2001"))
    ;
  }

  @Test
  @WithMockUser("TestUser1")
  @DisplayName("해석할 수 없는 RequestBody가 왔을 때")
  void systemFailBecauseCantParse() throws Exception {
    this.mockMvc.perform(RestDocumentationRequestBuilders.post("/board/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .content("Can't Parse Value Like this"))
        .andExpect(status().isBadRequest())
        .andDo(print())
    ;
  }*/
}
