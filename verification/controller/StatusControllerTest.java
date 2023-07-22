package com.gen.eChannel.verification.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gen.eChannel.verification.dto.StatusDto;
import com.gen.eChannel.verification.services.StatusService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Create Status Test")
    public void shouldCreateStatus() throws Exception {
        // given - precondition or setup
        StatusDto statusDto = new StatusDto();
        statusDto.setName("TestStatus");

        // when - action and behaviour that we are going to test
        when(statusService.CreateStatus(any(StatusDto.class))).thenReturn(statusDto);

        // then - verify the result and output using assert statements
        mockMvc.perform(post("/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statusDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(statusDto)));
    }

    @Test
    @DisplayName("Get All Status Test")
    public void shouldGetAllStatus() throws Exception {
        // given - precondition or setup
        List<StatusDto> statusDtoList = Collections.singletonList(new StatusDto("TestStatus"));

        // when - action and behaviour that we are going to test
        when(statusService.getAllStatus()).thenReturn(statusDtoList);

        // then - verify the result and output using assert statements
        mockMvc.perform(get("/status")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(statusDtoList)));
    }

    @Test
    @DisplayName("Get Status By ID Test")
    public void shouldGetStatusById() throws Exception {
        // given - precondition or setup
        StatusDto statusDto = new StatusDto();
        statusDto.setName("TestStatus");

        // when - action and behaviour that we are going to test
        when(statusService.getStatusById(any(Long.class))).thenReturn(statusDto);

        // then - verify the result and output using assert statements
        mockMvc.perform(get("/status/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(statusDto)));
    }
}
