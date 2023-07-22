package com.gen.signatureverification.dto;

import com.gen.signatureverification.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDto extends Auditable<String> {

    private String email;

    private String password;
}
