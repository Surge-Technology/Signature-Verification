package com.gen.signatureverification.services.impl;

import com.gen.signatureverification.dto.EventSourceDto;
import com.gen.signatureverification.dto.EventSourceSignatureVerificationDto;
import com.gen.signatureverification.dto.EventSourceStatusDto;
import com.gen.signatureverification.entities.EventSource;
import com.gen.signatureverification.entities.Status;
import com.gen.signatureverification.entities.User;
import com.gen.signatureverification.exceptions.ResourceNotFoundException;
import com.gen.signatureverification.repositories.EventSourceRepo;
import com.gen.signatureverification.repositories.StatusRepo;
import com.gen.signatureverification.repositories.UserRepo;
import com.gen.signatureverification.services.EventSourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventSourceServiceImpl implements EventSourceService {

    @Autowired
    private EventSourceRepo eventSourceRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public EventSourceServiceImpl(EventSourceRepo eventSourceRepo, StatusRepo statusRepo, UserRepo userRepo, ModelMapper modelMapper) {

        this.eventSourceRepo = eventSourceRepo;
        this.statusRepo = statusRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventSourceDto createEventSource(EventSourceDto eventSourceDto, String statusName, Long userId) {

        Optional<Status> statusOptional = statusRepo.findByName(statusName);
        if (statusOptional.isPresent()) {
            Status status = statusOptional.get();

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

            EventSource eventSource = modelMapper.map(eventSourceDto, EventSource.class);
            eventSource.setStatus(status);
            eventSource.setUser(user);

            EventSource savedEventSource = eventSourceRepo.save(eventSource);
            return modelMapper.map(savedEventSource, EventSourceDto.class);
        } else {
            throw new ResourceNotFoundException("Status not found");
        }
    }

    //done
    @Override
    public EventSourceDto getSignatureEventSourceById(Long signatureEventSourceId) {

        EventSource eventSource = eventSourceRepo.findById(signatureEventSourceId)
                .orElseThrow(() -> new ResourceNotFoundException("EventSource", "EventSource Id", signatureEventSourceId));

        return modelMapper.map(eventSource, EventSourceDto.class);
    }

    //done
    @Override
    public EventSourceDto updateSignatureEventSources(EventSourceDto eventSourceDto, Long userId, Long eventSourceId, String statusName) {

        Optional<EventSource> eventSourceOptional = eventSourceRepo.findById(eventSourceId);
        if (eventSourceOptional.isPresent()) {
            EventSource eventSource = eventSourceOptional.get();

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

            Optional<Status> statusOptional = statusRepo.findByName(statusName);

            if (statusOptional.isPresent()) {
                Status status = statusOptional.get();

                eventSource.setStatus(status);
            } else {
                throw new ResourceNotFoundException("Status not found");
            }



            // Save the updated eventSource entity back to the database
            EventSource updatedEventSource = eventSourceRepo.save(eventSource);
            return modelMapper.map(updatedEventSource, EventSourceDto.class);
        }

        throw new ResourceNotFoundException("EventSource", "EventSource Id", eventSourceId);

    }

    
    //done
	@Override
	public List<EventSourceSignatureVerificationDto> getAllSignatureVerificationByStatus(String statusName) {

		List<EventSource> eventSourceList = eventSourceRepo.findByStatusName(statusName);
		List<EventSourceSignatureVerificationDto> eventSourceSignatureVerificationDtos = new ArrayList<>();

		for (EventSource eventSource : eventSourceList) {
			EventSourceSignatureVerificationDto eventSourceSignatureVerificationDto = new EventSourceSignatureVerificationDto();
			eventSourceSignatureVerificationDto.setId(eventSource.getId());
			eventSourceSignatureVerificationDto.setCreatedOn(eventSource.getCreatedOn());
			eventSourceSignatureVerificationDto.setPriority(eventSource.getPriority());
			eventSourceSignatureVerificationDto.setSourceBu(eventSource.getSourceBu());
			eventSourceSignatureVerificationDto.setBusinessKey(eventSource.getBusinessKey());
			eventSourceSignatureVerificationDto.setDcpReference(eventSource.getDocumentCaptureReference());
			eventSourceSignatureVerificationDto.setAccountName(eventSource.getAccountShortName());
			eventSourceSignatureVerificationDto.setTransactionCurrency(eventSource.getTransactionCurrency());
			eventSourceSignatureVerificationDto.setTransactionAmount(eventSource.getTransactionAmount());

			eventSourceSignatureVerificationDtos.add(eventSourceSignatureVerificationDto);
		}

		return eventSourceSignatureVerificationDtos;
	}
    //done
    @Override
	public List<EventSourceDto> getAssignedSignatureEvents() {

		List<EventSource> selectRequests = eventSourceRepo.findByUserIsNotNull();
		List<EventSourceDto> eventSourceDtoList = new ArrayList<>();

		for (EventSource request : selectRequests) {

			Status assignedStatus = statusRepo.findByName("Assign")
					.orElseThrow(() -> new ResourceNotFoundException("Assign Not Found"));
			request.setStatus(assignedStatus);
			eventSourceRepo.save(request);

		}
		return eventSourceDtoList;
    }
    //done
    @Override
    public EventSourceStatusDto getSignatureVerificationStats() {

        long countUnassigned = eventSourceRepo.countByStatusName("Unassigned");

        long countSignatureOk = eventSourceRepo.countByStatusName("Proceed");

        long countSignatureNotOk = eventSourceRepo.countByStatusName("Reject");

        EventSourceStatusDto eventSourceStatusDto = new EventSourceStatusDto();
        eventSourceStatusDto.setNotVerified(countUnassigned);
        eventSourceStatusDto.setVerifiedOk(countSignatureOk);
        eventSourceStatusDto.setVerifiedNotOk(countSignatureNotOk);
        return eventSourceStatusDto;
    }

   
    //done
    @Override
	public void assignRequestsToCurrentUsers(List<Long> eventSignatureIds, Long userId, String statusName) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Optional<Status> statusOptional = statusRepo.findByName(statusName);

		eventSignatureIds.forEach(eventSourceId -> {

			EventSource eventSource = eventSourceRepo.findById(eventSourceId).get();

			eventSource.setUser(user);
			eventSource.setStatus(statusOptional.get());

			eventSourceRepo.save(eventSource);
		});

	}

    public List<EventSourceDto> getAssignedEventsByUserId(String statusName, Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        Optional<Status> statusOptional = statusRepo.findByName(statusName);
        if (statusOptional.isPresent()) {
            Status status = statusOptional.get();
            List<EventSource> eventSources = eventSourceRepo.findByStatus(status);

            return eventSources.stream()
                    .map(eventSource -> {
                        EventSourceDto dto = modelMapper.map(eventSource, EventSourceDto.class);
                        dto.setUserName(user.getUserName());
                        return dto;
                    })
                    .collect(Collectors.toList());
        } else {
            // Return an empty list or handle the case when the status is not found
            return new ArrayList<>();
        }
    }

}
