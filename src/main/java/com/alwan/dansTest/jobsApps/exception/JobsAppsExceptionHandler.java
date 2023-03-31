package com.alwan.dansTest.jobsApps.exception;

import com.alwan.dansTest.jobsApps.constant.ErrorCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order
@RestControllerAdvice
@Slf4j
public class JobsAppsExceptionHandler extends CommonExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(String code, String message, String idnMessage, String engMassage) {
        try {
            log.info("throw error bad request");
            var errorDetail = ErrorDetail.builder()
                    .errorCode(code)
                    .engMessage(engMassage)
                    .idnMessage(idnMessage)
                    .message(message)
                    .build();
            return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("exception 500");
            return exception(e);
        }
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> timeoutException(TimeoutException e) {
        log.info("Handling TimeoutException");
        return buildResponseEntity(ErrorCodeConstant.CODE_TIMEOUT, e.getMessage()
                , ErrorCodeConstant.TIMEOUT_ERROR_MESSAGE_IDN,ErrorCodeConstant.TIMEOUT_ERROR_MESSAGE_ENG);
    }

    @ExceptionHandler(EmailAlreadyRegistredException.class)
    public ResponseEntity<Object> emailAlreadyRegistredException(TimeoutException e) {
        log.info("Handling EmailAlreadyRegistredException");
        return buildResponseEntity(ErrorCodeConstant.CODE_EMAIL_ALREADY_REGISTRED, e.getMessage()
                , ErrorCodeConstant.EMAIL_ALREADY_REGISTRED_IDN,ErrorCodeConstant.EMAIL_ALREADY_REGISTRED_ENG);

    }

    @ExceptionHandler({UsernameAlreadyRegistredException.class})
    public ResponseEntity<Object> usernameAlreadyRegistredException(TimeoutException e) {
        log.info("Handling UsernameAlreadyRegistredException");
        return buildResponseEntity(ErrorCodeConstant.CODE_USERNAME_ALREADY_REGISTRED, e.getMessage()
                , ErrorCodeConstant.USERNAME_ALREADY_REGISTRED_IDN,ErrorCodeConstant.USERNAME_ALREADY_REGISTRED_ENG);

    }
}
