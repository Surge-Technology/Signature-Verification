package com.gen.eChannel.verification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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

import com.gen.eChannel.verification.dto.StatusDto;
import com.gen.eChannel.verification.entities.Status;
import com.gen.eChannel.verification.repositories.StatusRepo;
import com.gen.eChannel.verification.services.impl.StatusServiceImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatusServiceTest {

    private static final Long STATUS_ID = 1L;
    private static final String STATUS_NAME = "STATUS_NAME";

    @InjectMocks
    private StatusServiceImpl statusService;

    @Mock
    private StatusRepo statusRepo;

    @Mock
    private ModelMapper modelMapper;

    private StatusDto statusDto;
    private Status status;

    @BeforeEach
    public void setUp() {

        status = new Status();
        status.setId(STATUS_ID);
        status.setName(STATUS_NAME);

        statusDto = new StatusDto();
        statusDto.setId(STATUS_ID);
        statusDto.setName(STATUS_NAME);

        when(modelMapper.map(any(StatusDto.class), eq(Status.class))).thenReturn(status);
        when(modelMapper.map(any(Status.class), eq(StatusDto.class))).thenReturn(statusDto);
    }

    @Test
    @DisplayName("Create Status Test")
    public void shouldCreateStatus() {
        // given - precondition or setup
        when(statusRepo.save(any(Status.class))).thenAnswer(i -> i.getArgument(0));
        when(modelMapper.map(any(Status.class), eq(StatusDto.class))).thenReturn(statusDto);

        // when - action and behaviour that we are going to test
        StatusDto returnedDto = statusService.CreateStatus(statusDto);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(statusDto.getId(), returnedDto.getId());
        assertEquals(statusDto.getName(), returnedDto.getName());
    }

    @Test
    @DisplayName("Get Status By Id Test")
    public void shouldGetStatusById() {
        // given - precondition or setup
        when(statusRepo.findById(STATUS_ID)).thenReturn(Optional.of(status));
        when(modelMapper.map(status, StatusDto.class)).thenReturn(statusDto);

        // when - action and behaviour that we are going to test
        StatusDto returnedDto = statusService.getStatusById(STATUS_ID);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(statusDto.getId(), returnedDto.getId());
        assertEquals(statusDto.getName(), returnedDto.getName());
    }

    @Test
    @DisplayName("Get All Status Test")
    public void shouldGetAllStatus() {
        // given - precondition or setup
        List<Status> statusList = Collections.singletonList(status);
        when(statusRepo.findAll()).thenReturn(statusList);
        when(modelMapper.map(status, StatusDto.class)).thenReturn(statusDto);

        // when - action and behaviour that we are going to test
        List<StatusDto> returnedDtoList = statusService.getAllStatus();

        // then - verify the result and output using assert statements
        assertNotNull(returnedDtoList);
        assertFalse(returnedDtoList.isEmpty());
        assertEquals(statusDto.getId(), returnedDtoList.get(0).getId());
        assertEquals(statusDto.getName(), returnedDtoList.get(0).getName());
    }
}
