package com.desabi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RetryServiceImpl implements RetryService {

    private static final Logger log = LoggerFactory.getLogger(RetryServiceImpl.class);

    private int intentos = 0;

    @Retryable(
            value = {RuntimeException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 10000)
    )
    @Override
    public String retry() throws RuntimeException {
        log.info("Intento No: {}", intentos++);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/products/index";
        ResponseEntity<String> homeResponse = restTemplate.getForEntity(url, String.class);

        return homeResponse.getBody();
    }

    @Recover
    @Override
    public String recover(RuntimeException t) {
       log.error("Mensaje:{} ", t.getMessage());
        return t.getCause().toString();
    }

}
