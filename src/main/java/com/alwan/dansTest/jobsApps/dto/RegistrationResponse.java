package com.alwan.dansTest.jobsApps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistrationResponse implements Serializable {

    private Long status;
    private String message;
    private String username;
    private String email;
    private String name;
    private String token;
}
