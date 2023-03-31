package com.alwan.dansTest.jobsApps.constant;

public class ErrorCodeConstant {

    //Error Code
    public static final String CODE_TIMEOUT = "0001";
    public static final String CODE_EMAIL_ALREADY_REGISTRED = "0002";
    public static final String CODE_USERNAME_ALREADY_REGISTRED = "0003";

    //IND MESSAGE
    public static final String TIMEOUT_ERROR_MESSAGE_IDN = "Timeout dalam request ke url";
    public static final Object UNAUTHORIZED_MESSAGE_IDN = "Tidak ada authorisasi";

    public static final String EMAIL_ALREADY_REGISTRED_IDN = "Email telah digunakan";
    public static final String USERNAME_ALREADY_REGISTRED_IDN = "Username telah digunakan";

    //ENG MESSAGE
    public static final String TIMEOUT_ERROR_MESSAGE_ENG = "Timeout after request to url";

    public static final Object UNAUTHORIZED_MESSAGE_ENG = "Unauthorized";
    public static final String EMAIL_ALREADY_REGISTRED_ENG = "Email already registred";
    public static final String USERNAME_ALREADY_REGISTRED_ENG = "Username already registred";
}
