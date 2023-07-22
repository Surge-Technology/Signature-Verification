package com.gen.eChannel.verification.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.gen.eChannel.verification.dto.AssignRequestDto;
import com.gen.eChannel.verification.dto.EventSourceDto;
import com.gen.eChannel.verification.dto.EventSourceSignatureVerificationDto;
import com.gen.eChannel.verification.dto.EventSourceStatusDto;
import com.gen.eChannel.verification.dto.StatusDto;
import com.gen.eChannel.verification.dto.UserDto;
import com.gen.eChannel.verification.services.EventSourceService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EventSourceControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private EventSourceService eventSourceService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @DisplayName("Create Event Source Test")
        public void shouldCreateEventSource() throws Exception {
                // given - precondition or setup
                EventSourceDto eventSourceDto = new EventSourceDto();

                // when - action and behaviour that we are going to test
                when(eventSourceService.createEventSource(any(EventSourceDto.class), anyString(), anyLong()))
                                .thenReturn(eventSourceDto);

                // then - verify the result and output using assert statements
                mockMvc.perform(post("/user/1/eventSource/status/Status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(eventSourceDto)))
                                .andExpect(status().isCreated())
                                .andExpect(content().json(objectMapper.writeValueAsString(eventSourceDto)));
        }

        @Test
        @DisplayName("Get Event Source By Id Test")
        public void shouldGetEventSourceById() throws Exception {
                // given - precondition or setup
                EventSourceDto eventSourceDto = new EventSourceDto();

                // when - action and behaviour that we are going to test
                when(eventSourceService.getSignatureEventSourceById(anyLong())).thenReturn(eventSourceDto);

                // then - verify the result and output using assert statements
                mockMvc.perform(get("/eventSource/1"))
                                .andExpect(status().isOk())
                                .andExpect(content().json(objectMapper.writeValueAsString(eventSourceDto)));
        }

        @Test
        @DisplayName("Update Event Sources Test")
        public void shouldUpdateEventSources() throws Exception {
                // given - precondition or setup
                EventSourceDto eventSourceDto = new EventSourceDto();
                eventSourceDto.setBusinessKey("sampleBusinessKey");
                eventSourceDto.setUserName("sampleUserName");
                eventSourceDto.setPriority("samplePriority");
                eventSourceDto.setSourceBu("sampleSourceBu");
                eventSourceDto.setApplication("sampleApplication");
                eventSourceDto.setTransactionAmount(100.0);
                eventSourceDto.setTransactionCurrency("sampleTransactionCurrency");
                eventSourceDto.setAmountInMur(200.0);
                eventSourceDto.setAccountShortName("sampleShortName");
                eventSourceDto.setDebitAccountNumber("sampleDebitAccountNumber");
                eventSourceDto.setDebitAccountCurrency("sampleDebitAccountCurrency");
                eventSourceDto.setPaymentDetails1("samplePaymentDetails1");
                eventSourceDto.setPaymentDetails2("samplePaymentDetails2");
                eventSourceDto.setPaymentDetails3("samplePaymentDetails3");
                eventSourceDto.setPaymentDetails4("samplePaymentDetails4");
                eventSourceDto.setVerified("Verified");
                eventSourceDto.setDiscrepancyReason("No discrepancy");
                eventSourceDto.setDocumentCaptureReference("sampleReference");
                eventSourceDto.setComments("sampleComments");
                StatusDto status = new StatusDto();
                eventSourceDto.setStatus(status);
                UserDto user = new UserDto();
                eventSourceDto.setUser(user);
                eventSourceDto.setUpdatedOn(LocalDateTime.now());

                // when - action and behaviour that we are going to test
                when(eventSourceService.updateSignatureEventSources(any(EventSourceDto.class), anyLong(), anyLong(),
                                anyString())).thenReturn(eventSourceDto);

                // then - verify the result and output using assert statements
                mockMvc.perform(put("/user/1/eventSource/1/status/Status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(eventSourceDto)))
                                .andExpect(status().isCreated())
                                .andExpect(content().json(objectMapper.writeValueAsString(eventSourceDto)));
        }

        @Test
        @DisplayName("Get All Signature Verification By Status Test")
        public void shouldGetAllSignatureVerificationByStatus() throws Exception {
                // given - precondition or setup
                List<EventSourceSignatureVerificationDto> eventSourceEchannelVerificationDtos = new ArrayList<>();

                // when - action and behaviour that we are going to test
                when(eventSourceService.getAllSignatureVerificationByStatus(anyString()))
                                .thenReturn(eventSourceEchannelVerificationDtos);

                // then - verify the result and output using assert statements
                mockMvc.perform(get("/by-status-name/Unassigned"))
                                .andExpect(status().isOk())
                                .andExpect(content().json(
                                                objectMapper.writeValueAsString(eventSourceEchannelVerificationDtos)));
        }

        @Test
        @DisplayName("Assign Requests To Current User Test")
        public void shouldAssignRequestsToCurrentUser() throws Exception {
                // given - precondition or setup
                AssignRequestDto assignRequestDto = new AssignRequestDto();
                assignRequestDto.setEventSourceId(Arrays.asList(1L, 2L));

                // when - action and behaviour that we are going to test
                doNothing().when(eventSourceService).assignRequestsToCurrentUsers(anyList(), anyLong(), anyString());

                // then - verify the result and output using assert statements
                mockMvc.perform(post("/requests/assign/user/1/status/Assign")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(assignRequestDto)))
                                .andExpect(status().isOk())
                                .andExpect(content().string("Requests assigned successfully."));
        }

        @Test
        @DisplayName("Get Event Source Status Count Test")
        public void shouldGetEventSourceStatusCount() throws Exception {
                // given - precondition or setup
                EventSourceStatusDto eventSourceStatusDto = new EventSourceStatusDto();

                // when - action and behaviour that we are going to test
                when(eventSourceService.getSignatureVerificationStats()).thenReturn(eventSourceStatusDto);

                // then - verify the result and output using assert statements
                mockMvc.perform(get("/eventSourceStatus/count"))
                                .andExpect(status().isOk())
                                .andExpect(content().json(objectMapper.writeValueAsString(eventSourceStatusDto)));
        }

        @Test
        @DisplayName("Get Assigned Events Test")
        public void shouldGetAssignedEvents() throws Exception {
                // given - precondition or setup
                List<EventSourceDto> eventSourceDtos = new ArrayList<>();

                // when - action and behaviour that we are going to test
                when(eventSourceService.getAssignedSignatureEvents()).thenReturn(eventSourceDtos);

                // then - verify the result and output using assert statements
                mockMvc.perform(get("/requests/assigned"))
                                .andExpect(status().isOk())
                                .andExpect(content().json(objectMapper.writeValueAsString(eventSourceDtos)));
        }

        @Test
        @DisplayName("Get Assigned Events By User Id Test")
        public void shouldGetAssignedEventsByUserId() throws Exception {
                // given - precondition or setup
                List<EventSourceDto> eventSourceDtos = new ArrayList<>();

                // when - action and behaviour that we are going to test
                when(eventSourceService.getAssignedEventsByUserId(anyString(), anyLong())).thenReturn(eventSourceDtos);

                // then - verify the result and output using assert statements
                mockMvc.perform(get("/user/1/status/Status"))
                                .andExpect(status().isOk())
                                .andExpect(content().json(objectMapper.writeValueAsString(eventSourceDtos)));
        }
}
