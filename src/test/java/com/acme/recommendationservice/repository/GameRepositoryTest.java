package com.acme.recommendationservice.repository;

import com.acme.recommendationservice.model.Customer;
import com.acme.recommendationservice.model.Game;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTest
{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void test()
    {
        Game game1 = new Game();
        game1.setName("Bingo");
        gameRepository.save(game1);

        Game game2 = new Game();
        game2.setName("Lotto");
        gameRepository.save(game2);

        Customer customer = new Customer();
        customer.setRecommendations(Arrays.asList(game1, game2));

        customerRepository.save(customer);

        List<Game> recommendations = gameRepository.findByCustomerId(1L, new PageRequest(0, 1));
        assertThat(recommendations, IsCollectionWithSize.hasSize(1));
    }

}