package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.model.Game;
import com.acme.recommendationservice.repository.GameRepository;
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

    private final GameRepository gameRepository;


    @Autowired
    public RecommendationController(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }


    @GetMapping("/customers/{customerId}/games/recommendations")
    public List<Game> getRecommendationsByCustomer(
        @PathVariable("customerId") Long customerId,
        @RequestParam("count") Integer count
    )
    {
        Pageable pageable = new PageRequest(0, count);
        return gameRepository.findByCustomerId(customerId, pageable);
    }
}
