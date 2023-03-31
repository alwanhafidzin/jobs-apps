package com.alwan.dansTest.jobsApps.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    public CommonExceptionHandler() {
    }

    @ExceptionHandler({CommonRestException.class})
    public ResponseEntity<? extends ErrorDetail> commonRestException(CommonRestException e) {
        log.info("Exception is commonRestException, message : {}", e.getMessage());
        return new ResponseEntity((ErrorDetail)e.getResponseEntity().getBody(), e.getResponseEntity().getStatusCode());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exception(Exception e) {
        log.error("Exception is UNCAUGHT, details : ", e);
        ErrorDetail errorDetail = ErrorDetail.builder().errorCode("0000").message(e.getMessage()).engMessage("Something went wrong").idnMessage("Terjadi kesalahan").build();
        return new ResponseEntity(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
