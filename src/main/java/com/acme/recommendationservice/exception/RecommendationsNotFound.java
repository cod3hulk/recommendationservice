package com.acme.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecommendationsNotFound extends RuntimeException
{
    public RecommendationsNotFound(Long customerId)
    {
        super(String.format("No active recommendations found for customerId %d", customerId));
    }
}
