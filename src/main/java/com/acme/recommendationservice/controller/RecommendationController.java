package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.repository.RecommendationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController
{

    private final RecommendationRepository recommendationRepository;


    @Autowired
    public RecommendationController(RecommendationRepository recommendationRepository)
    {
        this.recommendationRepository = recommendationRepository;
    }


    @GetMapping("/customers/{customerId}/games/recommendations")
    public List<Recommendation> getRecommendationsByCustomer(
        @PathVariable("customerId") Long customerId,
        @RequestParam("count") Integer count
    )
    {
        // TODO return 404 if no recommendation is found
        // TODO return 404 if recommendation_active is false
        Pageable pageable = new PageRequest(0, count);
        return recommendationRepository.findByCustomerId(customerId, pageable);
    }

    // TODO provide CSV upload
}
