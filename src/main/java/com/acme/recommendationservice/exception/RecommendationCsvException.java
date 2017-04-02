package com.acme.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RecommendationCsvException extends RuntimeException
{
    public RecommendationCsvException(Exception e)
    {
        super(String.format("Error processing recommendations csv file [%s]", e.getMessage()), e);
    }
}
