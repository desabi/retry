package com.desabi.service;

import org.springframework.stereotype.Service;

@Service
public interface RetryService {
	String retry() throws RuntimeException;
    String recover(RuntimeException t);
}
