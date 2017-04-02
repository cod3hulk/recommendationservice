package com.acme.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RecommendationCsvError extends RuntimeException
{
    public RecommendationCsvError(Exception e)
    {
        super(String.format("Error processing recommendations csv file [%s]", e.getMessage()), e);
    }
}
