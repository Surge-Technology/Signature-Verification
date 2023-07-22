package com.gen.signatureverification.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventSourceStatusDto {

    private long notVerified;

    private long verifiedOk;

    private long verifiedNotOk;

}
