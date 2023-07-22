package com.gen.signatureverification.dto;

import com.gen.signatureverification.util.Auditable;
import lombok.*;

import javax.validation.Valid;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto extends Auditable<String> {

    @Valid
    private String name;

}
