package com.alwan.dansTest.jobsApps.exception;

public class EmailAlreadyRegistredException  extends RuntimeException{
    public EmailAlreadyRegistredException(String message) {
        super(message);
    }
}
