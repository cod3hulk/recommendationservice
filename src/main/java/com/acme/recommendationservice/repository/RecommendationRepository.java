package com.acme.recommendationservice.repository;

import com.acme.recommendationservice.model.Recommendation;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RecommendationRepository extends CrudRepository<Recommendation, Long>
{
    List<Recommendation> findByCustomerId(Long customerId, Pageable pageable);
}
