package com.alwan.dansTest.jobsApps.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public class CommonRestException extends RestClientException {
    protected ResponseEntity<? extends ErrorDetail> responseEntity;

    public CommonRestException(String message) {
        super(message);
    }

    public ResponseEntity<? extends ErrorDetail> getResponseEntity() {
        return this.responseEntity;
    }

    public void setResponseEntity(ResponseEntity<? extends ErrorDetail> responseEntity) {
        this.responseEntity = responseEntity;
    }
}
