package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.exception.RecommendationsNotFound;
import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.service.RecommendationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
class RecommendationController
{

    private final RecommendationService recommendationService;


    @Autowired
    public RecommendationController(
        RecommendationService recommendationService
    )
    {
        this.recommendationService = recommendationService;
    }


    @GetMapping("/customers/{customerId}/games/recommendations")
    public List<Recommendation> getRecommendationsByCustomer(
        @PathVariable("customerId") Long customerId,
        @RequestParam(value = "count", defaultValue = "10") Integer limit
    )
    {
        List<Recommendation> recommendations = recommendationService.findByCustomerId(customerId, limit);
        if (recommendations.isEmpty())
        {
            throw new RecommendationsNotFound(customerId);
        }
        return recommendations;
    }


    @PostMapping("/recommendations")
    public void uploadReommendations(@RequestParam("file") MultipartFile file)
    {
        recommendationService.saveCsvData(file);
    }
}
