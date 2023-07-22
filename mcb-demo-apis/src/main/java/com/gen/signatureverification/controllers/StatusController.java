package com.gen.signatureverification.controllers;

import com.gen.signatureverification.dto.StatusDto;
import com.gen.signatureverification.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("/status")
    public ResponseEntity<StatusDto> CreateStatus(@Valid @RequestBody StatusDto statusDto) {

        StatusDto createStatusDto = statusService.CreateStatus(statusDto);
        return new ResponseEntity<>(createStatusDto, HttpStatus.CREATED);
    }

    @GetMapping("/status")
    public ResponseEntity<List<StatusDto>> getAllStatus() {

        List<StatusDto> statusDtoList = statusService.getAllStatus();
        return new ResponseEntity<>(statusDtoList, HttpStatus.OK);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Long statusId) {

        StatusDto statusDto = statusService.getStatusById(statusId);
        return new ResponseEntity<>(statusDto, HttpStatus.OK);

    }
}
