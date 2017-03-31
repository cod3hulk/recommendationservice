package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.model.Game;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController
{

    private static final Map<Long, List<Game>> recommendationsByCustomer = new HashMap<>();

    static
    {
        Game bingo = new Game();
        bingo.setId(1L);
        bingo.setName("bingo");

        Game cashwheel = new Game();
        cashwheel.setId(2L);
        cashwheel.setName("cashwheel");

        Game cashbuster = new Game();
        cashbuster.setId(3L);
        cashbuster.setName("cashbuster");

        recommendationsByCustomer.put(1L, Arrays.asList(bingo, cashwheel));
        recommendationsByCustomer.put(2L, Arrays.asList(cashbuster, cashwheel));
    }

    @GetMapping("/customers/{customerId}/games/recommendations")
    public List<Game> getRecommendationsByCustomer(
        @PathVariable("customerId") Long customerId,
        @RequestParam("count") Long count
    )
    {
        return recommendationsByCustomer.get(customerId);
    }
}
