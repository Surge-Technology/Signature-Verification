package com.gen.eChannel.verification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.gen.eChannel.verification.dto.EventSourceDto;
import com.gen.eChannel.verification.dto.EventSourceSignatureVerificationDto;
import com.gen.eChannel.verification.dto.EventSourceStatusDto;
import com.gen.eChannel.verification.entities.EventSource;
import com.gen.eChannel.verification.entities.Status;
import com.gen.eChannel.verification.entities.User;
import com.gen.eChannel.verification.repositories.EventSourceRepo;
import com.gen.eChannel.verification.repositories.StatusRepo;
import com.gen.eChannel.verification.repositories.UserRepo;
import com.gen.eChannel.verification.services.impl.EventSourceServiceImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventSourceServiceTest {

    private static final Long EVENT_SOURCE_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String STATUS_NAME = "STATUS_NAME";

    @InjectMocks
    private EventSourceServiceImpl eventSourceService;

    @Mock
    private EventSourceRepo eventSourceRepo;

    @Mock
    private StatusRepo statusRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private Status status;
    private EventSourceDto eventSourceDto;
    private EventSource eventSource;

    @BeforeEach
    public void setUp() {

        user = new User();
        user.setId(USER_ID);

        status = new Status();
        status.setName(STATUS_NAME);

        eventSource = new EventSource();
        eventSource.setId(EVENT_SOURCE_ID);

        eventSourceDto = new EventSourceDto();
        eventSourceDto.setId(EVENT_SOURCE_ID);

        when(modelMapper.map(any(EventSourceDto.class), eq(EventSource.class))).thenReturn(eventSource);
        when(modelMapper.map(any(EventSource.class), eq(EventSourceDto.class))).thenReturn(eventSourceDto);
    }

    @Test
    @DisplayName("Create Event Source Test")
    public void shouldCreateEventSource() {
        // given - precondition or setup
        when(statusRepo.findByName(STATUS_NAME)).thenReturn(Optional.of(status));
        when(userRepo.findById(USER_ID)).thenReturn(Optional.of(user));
        when(eventSourceRepo.save(any(EventSource.class))).thenAnswer(i -> i.getArgument(0));
        when(modelMapper.map(any(EventSource.class), eq(EventSourceDto.class))).thenReturn(eventSourceDto);

        // when - action and behaviour that we are going to test
        EventSourceDto returnedDto = eventSourceService.createEventSource(eventSourceDto, STATUS_NAME, USER_ID);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(eventSourceDto.getId(), returnedDto.getId());
    }

    @Test
    @DisplayName("Get Signature Event Source By Id Test")
    public void shouldGetSignatureEventSourceById() {
        // given - precondition or setup
        when(eventSourceRepo.findById(EVENT_SOURCE_ID)).thenReturn(Optional.of(eventSource));
        when(modelMapper.map(eventSource, EventSourceDto.class)).thenReturn(eventSourceDto);

        // when - action and behaviour that we are going to test
        EventSourceDto returnedDto = eventSourceService.getSignatureEventSourceById(EVENT_SOURCE_ID);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(eventSourceDto.getId(), returnedDto.getId());
    }

    @Test
    @DisplayName("Update Signature Event Sources Test")
    public void shouldUpdateSignatureEventSources() {
        // given - precondition or setup
        when(eventSourceRepo.findById(EVENT_SOURCE_ID)).thenReturn(Optional.of(eventSource));
        when(userRepo.findById(USER_ID)).thenReturn(Optional.of(user));
        when(statusRepo.findByName(STATUS_NAME)).thenReturn(Optional.of(status));
        when(eventSourceRepo.save(any(EventSource.class))).thenAnswer(i -> i.getArgument(0));
        when(modelMapper.map(any(EventSource.class), eq(EventSourceDto.class))).thenReturn(eventSourceDto);

        // when - action and behaviour that we are going to test
        EventSourceDto returnedDto = eventSourceService.updateSignatureEventSources(eventSourceDto, USER_ID, EVENT_SOURCE_ID, STATUS_NAME);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(eventSourceDto.getId(), returnedDto.getId());
    }

    @Test
    @DisplayName("Get All Signature Verification By Status Test")
    public void shouldGetAllSignatureVerificationByStatus() {
        // given - precondition or setup
        List<EventSource> eventSourceList = new ArrayList<>();
        EventSource mockEventSource = mock(EventSource.class);
        when(mockEventSource.getTransactionAmount()).thenReturn(100.00);
        eventSourceList.add(mockEventSource);

        // when - action and behaviour that we are going to test
        when(eventSourceRepo.findByStatusName(anyString())).thenReturn(eventSourceList);
        List<EventSourceSignatureVerificationDto> eventSourceSignatureVerificationDtos = eventSourceService
                .getAllSignatureVerificationByStatus(STATUS_NAME);

        // then - verify the result and output using assert statements
        assertNotNull(eventSourceSignatureVerificationDtos);
        assertEquals(1, eventSourceSignatureVerificationDtos.size());
    }

    @Test
    @DisplayName("Get Assigned Signature Events Test")
    public void shouldGetAssignedSignatureEvents() {
        // given - precondition or setup
        List<EventSource> selectRequests = Collections.singletonList(eventSource);
        when(eventSourceRepo.findByUserIsNotNull()).thenReturn(selectRequests);
        when(statusRepo.findByName("Assign")).thenReturn(Optional.of(status));

        List<EventSourceDto> eventSourceDtoList = eventSourceService.getAssignedSignatureEvents();

        // then - verify the result and output using assert statements
        assertNotNull(eventSourceDtoList);
    }

    @Test
    @DisplayName("Get Signature Verification Stats Test")
    public void shouldGetSignatureVerificationStats() {
        // given - precondition or setup
        when(eventSourceRepo.countByStatusName("Unassigned")).thenReturn(10L);
        when(eventSourceRepo.countByStatusName("Proceed")).thenReturn(5L);
        when(eventSourceRepo.countByStatusName("Reject")).thenReturn(3L);


        // when - action and behaviour that we are going to test
        EventSourceStatusDto eventSourceStatusDto = eventSourceService.getSignatureVerificationStats();

        // then - verify the result and output using assert statements
        assertNotNull(eventSourceStatusDto);
        assertEquals(10L, eventSourceStatusDto.getNotVerified());
        assertEquals(5L, eventSourceStatusDto.getVerifiedOk());
        assertEquals(3L, eventSourceStatusDto.getVerifiedNotOk());
    }

    @Test
    @DisplayName("Assign Requests To Current Users Test")
    public void shouldAssignRequestsToCurrentUsers() {
        // given - precondition or setup
        List<Long> eventSignatureIds = Arrays.asList(EVENT_SOURCE_ID);
        when(userRepo.findById(USER_ID)).thenReturn(Optional.of(user));
        when(statusRepo.findByName(STATUS_NAME)).thenReturn(Optional.of(status));
        when(eventSourceRepo.findById(EVENT_SOURCE_ID)).thenReturn(Optional.of(eventSource));


        // when - action and behaviour that we are going to test
        eventSourceService.assignRequestsToCurrentUsers(eventSignatureIds, USER_ID, STATUS_NAME);

        // then - verify the result and output using assert statements
        verify(eventSourceRepo, times(1)).save(any(EventSource.class));
    }

    @Test
    @DisplayName("Get Assigned Events By User Id Test")
    public void shouldGetAssignedEventsByUserId() {
        // given - precondition or setup
        when(userRepo.findById(USER_ID)).thenReturn(Optional.of(user));
        when(statusRepo.findByName(STATUS_NAME)).thenReturn(Optional.of(status));
        when(eventSourceRepo.findByStatus(status)).thenReturn(Collections.singletonList(eventSource));
        when(modelMapper.map(any(EventSource.class), eq(EventSourceDto.class))).thenReturn(eventSourceDto);

        // when - action and behaviour that we are going to test
        List<EventSourceDto> returnedDtos = eventSourceService.getAssignedEventsByUserId(STATUS_NAME, USER_ID);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDtos);
        assertEquals(1, returnedDtos.size());
    }
}
