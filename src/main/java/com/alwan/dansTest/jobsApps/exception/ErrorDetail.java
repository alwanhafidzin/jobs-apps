package com.alwan.dansTest.jobsApps.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorDetail implements Serializable {
    private String errorCode;
    private String message;
    private String engMessage;
    private String idnMessage;
}
