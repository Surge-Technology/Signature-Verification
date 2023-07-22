package com.gen.signatureverification.dto;

import com.gen.signatureverification.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddressDto extends Auditable<String> {

    @NotBlank(message = "Please provide address Line 1")
    @Size(min = 3, message = "address line 1 must be atleast 10 characters")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "Please provide city")
    private String city;

    @NotBlank(message = "Please provide pinCode")
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Please provide US pinCode")
    private String pinCode;

    @NotBlank(message = "Please provide state")
    private String state;
}
