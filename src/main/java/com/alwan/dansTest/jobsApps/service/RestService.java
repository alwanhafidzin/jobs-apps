package com.alwan.dansTest.jobsApps.service;

import com.alwan.dansTest.jobsApps.exception.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    public <T> T get(String url, Class<T> responseBody) {
        T responseObject;
        try {
            log.info("request url : {}", url);
            ResponseEntity<T> response = restTemplate.getForEntity(url, responseBody);
            responseObject = response.getBody();
            log.info("response : {} ", responseObject);
        } catch (Exception e) {
            log.error("timeout : ", e);
            throw new TimeoutException("timeout when calling from url :" + url);
        }
        return responseObject;
    }
}
