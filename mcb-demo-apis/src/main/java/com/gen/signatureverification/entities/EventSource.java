package com.gen.signatureverification.entities;

import com.gen.signatureverification.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventSource extends Auditable<String> {

    @NotBlank(message = "Please enter businessKey")
    private String businessKey;

    @NotBlank(message = "Please enter priority")
    private String priority;

    @NotBlank(message = "Please enter sourceBu")
    private String sourceBu;

    @NotBlank(message = "Please enter application")
    private String application;

    //@NotBlank(message = "Please enter transactionAmount")
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private LocalDateTime updatedOn;

}
