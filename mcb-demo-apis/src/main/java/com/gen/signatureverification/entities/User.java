package com.gen.signatureverification.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gen.signatureverification.util.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends Auditable<String> {

    @NotEmpty
    @Size(min = 2, message = "User name must be min of 2 characters !!")
    private String userName;

    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter valid email address email@domainname.domain")
    private String email;


    @NotEmpty
    @Size(min = 3, message = "Password must be min of 3 chars !!")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}