package com.acme.recommendationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController
{

    @GetMapping("/customers/{customerId}/games/recommendations")
    public void getGameRecommendationsByCustomer(
        @PathVariable("customerId") Long customerId,
        @RequestParam("count") Long count
    )
    {

    }
}
