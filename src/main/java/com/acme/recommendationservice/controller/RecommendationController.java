package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.repository.RecommendationRepository;
import com.acme.recommendationservice.service.RecommendationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
class RecommendationController
{

    private final RecommendationRepository recommendationRepository;

    private final RecommendationService recommendationService;


    @Autowired
    public RecommendationController(
        RecommendationRepository recommendationRepository,
        RecommendationService recommendationService
    )
    {
        this.recommendationRepository = recommendationRepository;
        this.recommendationService = recommendationService;
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


    @PostMapping("/recommendations")
    public void uploadReommendations(@RequestParam("file") MultipartFile file)
    {
        recommendationService.saveCsvData(file);
    }
}
