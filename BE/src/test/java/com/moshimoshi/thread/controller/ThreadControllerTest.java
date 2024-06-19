package com.moshimoshi.thread.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.FilterConfig;
import com.moshimoshi.common.dto.BaseResponse;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.thread.dto.ThreadResponse;
import com.moshimoshi.thread.service.ThreadService;
import com.moshimoshi.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = ThreadController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = FilterConfig.class)
)
class ThreadControllerTest {
    @MockBean
    ThreadService threadService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("스레드 조회")
    @Test
    void show() throws Exception {
        //given
        Long threadId = 1L;
        Thread thread = Thread.builder()
                .id(threadId)
                .writer(new User())
                .build();

        when(threadService.findOne(threadId))
                .thenReturn(thread);

        //when
        ResultActions resultActions = mockMvc.perform(
                get("/api/threads/" + threadId)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("data.threadId").value(threadId));
    }

    @DisplayName("스레드 작성")
    @Test
    void post() throws Exception {
        //given
        ThreadPostRequest request = threadPostRequest();

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/threads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );

        //then
        resultActions.andExpect(status().isOk());
    }

    private ThreadPostRequest threadPostRequest() {
        return ThreadPostRequest.builder()
                .content("안녕하세요")
                .anonymous(true)
                .build();
    }
}