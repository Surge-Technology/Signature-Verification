package com.gen.signatureverification.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gen.signatureverification.util.Auditable;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventSourceDto extends Auditable<String> {

    @NotBlank(message = "Please enter businessKey")
    private String businessKey;

    @JsonIgnore
    private String userName;

    @NotBlank(message = "Please enter priority")
    private String priority;

    @NotBlank(message = "Please enter sourceBu")
    private String sourceBu;

    @NotBlank(message = "Please enter application")
    private String application;

   // @NotBlank(message = "Please enter transactionAmount")
    private Double transactionAmount;

    @NotBlank(message = "Please enter transactionCurrency")
    private String transactionCurrency;

    @NotNull(message = "Please enter amountInMur")
    private Double amountInMur;

    @NotBlank(message = "Please enter accountShortName")
    private String accountShortName;

    @NotBlank(message = "Please enter debitAccountNumber")
    private String debitAccountNumber;

    @NotBlank(message = "Please enter debitAccountCurrency")
    private String debitAccountCurrency;

    @NotBlank(message = "Please enter paymentDetails1")
    private String paymentDetails1;

    @NotBlank(message = "Please enter paymentDetails2")
    private String paymentDetails2;

    @NotBlank(message = "Please enter paymentDetails3")
    private String paymentDetails3;

    @NotBlank(message = "Please enter paymentDetails4")
    private String paymentDetails4;

    @NotBlank(message = "Please enter verified")
    private String verified;

    @NotBlank(message = "Please enter discrepancyReason")
    private String discrepancyReason;

    @NotBlank(message = "Please enter your documentCaptureReference")
    private String documentCaptureReference;

    @NotBlank(message = "Please enter comments")
    private String comments;

    private StatusDto status;
    
    private UserDto user;

    private LocalDateTime updatedOn;


}
