package com.acme.recommendationservice.repository;

import com.acme.recommendationservice.model.Recommendation;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecommendationRepositoryTest
{

    @Autowired
    private RecommendationRepository objectUnderTest;


    @Test
    @Sql("classpath:db/test-recommendation-data.sql")
    public void findByCustomerId()
    {
        // GIVEN
        Long customerId = 1L;
        Pageable pageable = new PageRequest(0, 1);

        // WHEN
        List<Recommendation> recommendations = objectUnderTest.findByCustomerId(customerId, pageable);

        // THEN
        assertThat(recommendations, hasSize(1));
    }
}