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
public class RegistrationRequest implements Serializable {

    private String username;
    private String name;
    private String email;
    private String password;
}
