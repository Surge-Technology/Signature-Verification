package com.gen.signatureverification.controllers;

import com.gen.signatureverification.dto.AssignRequestDto;
import com.gen.signatureverification.dto.EventSourceDto;
import com.gen.signatureverification.dto.EventSourceSignatureVerificationDto;
import com.gen.signatureverification.dto.EventSourceStatusDto;
import com.gen.signatureverification.services.EventSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class EventSourceController {

    @Autowired
    EventSourceService eventSourceService;


    @PostMapping("/user/{userId}/eventSource/status/{statusName}")
    public ResponseEntity<EventSourceDto> createEventSource(@PathVariable String statusName, @PathVariable Long userId, @RequestBody EventSourceDto eventSourceDto) {

        EventSourceDto eventSourceDto1 = eventSourceService.createEventSource(eventSourceDto, statusName, userId);
        return new ResponseEntity<>(eventSourceDto1, HttpStatus.CREATED);
    }


    @GetMapping("/eventSource/{signatureEventSourceId}")
    public ResponseEntity<EventSourceDto> getSignatureEventSourceById(@PathVariable Long signatureEventSourceId) {

        EventSourceDto eventSourceDto = eventSourceService.getSignatureEventSourceById(signatureEventSourceId);
        return new ResponseEntity<>(eventSourceDto, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/eventSource/{eventSourceId}/status/{statusName}")
    public ResponseEntity<EventSourceDto> updateSignatureEventSources(@Valid @RequestBody EventSourceDto eventSourceDto, @PathVariable Long userId, @PathVariable Long eventSourceId, @PathVariable String statusName) {

        EventSourceDto updatedEventSource = eventSourceService.updateSignatureEventSources(eventSourceDto, userId, eventSourceId, statusName);
        return new ResponseEntity<>(updatedEventSource, HttpStatus.CREATED);
    }

   

    @GetMapping("/by-status-name/{statusName}")
    public ResponseEntity<List<EventSourceSignatureVerificationDto>> getAllSignatureVerificationByStatus(@PathVariable String statusName) {

        List<EventSourceSignatureVerificationDto> eventSourceEchannelVerificationDtos = eventSourceService.getAllSignatureVerificationByStatus(statusName);
        return new ResponseEntity<>(eventSourceEchannelVerificationDtos, HttpStatus.OK);

    }

    @PostMapping("/requests/assign/user/{userId}/status/{statusName}")
    public ResponseEntity<String> assignRequestsToCurrentUser(@RequestBody AssignRequestDto assignRequestDto, @PathVariable Long userId, @PathVariable String statusName) {

        // Assign the selected requests to the current user
        eventSourceService.assignRequestsToCurrentUsers(assignRequestDto.getEventSourceId(), userId, statusName);

        return ResponseEntity.ok("Requests assigned successfully.");
    }



    @GetMapping("/eventSourceStatus/count")
    public ResponseEntity<EventSourceStatusDto> getSignatureVerificationStats() {

        EventSourceStatusDto eventSourceStatusDto = eventSourceService.getSignatureVerificationStats();

        return new ResponseEntity<>(eventSourceStatusDto, HttpStatus.OK);
    }

    @GetMapping("/requests/assigned")
    public ResponseEntity<List<EventSourceDto>> getAssignedSignatureEvents() {
        List<EventSourceDto> assignedEvents = eventSourceService.getAssignedSignatureEvents();
        return ResponseEntity.ok(assignedEvents);
    }

    @GetMapping("user/{userId}/status/{statusName}")
    public ResponseEntity<List<EventSourceDto>> getAssignedEventsByUserId(@PathVariable String statusName, @PathVariable Long userId) {
        List<EventSourceDto> assignedEvents = eventSourceService.getAssignedEventsByUserId(statusName,userId);
        return ResponseEntity.ok(assignedEvents);
    }

}
