package com.acme.recommendationservice.repository;

import com.acme.recommendationservice.model.Game;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long>
{
    @Query("SELECT g FROM Customer c LEFT JOIN c.recommendations g WHERE c.id = ?1")
    List<Game> findByCustomerId(Long customerId, Pageable pageable);
}
