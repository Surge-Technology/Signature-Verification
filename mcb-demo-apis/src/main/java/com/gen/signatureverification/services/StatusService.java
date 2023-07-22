package com.gen.signatureverification.services;

import com.gen.signatureverification.dto.StatusDto;

import java.util.List;

public interface StatusService {

    StatusDto CreateStatus(StatusDto statusDto);

    List<StatusDto> getAllStatus();

    StatusDto getStatusById(Long statusId);


}
