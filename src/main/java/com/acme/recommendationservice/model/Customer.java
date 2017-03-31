package com.acme.recommendationservice.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer
{
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinTable(
        name = "customer_recommendation",
        joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id", unique = true)}
    )
    private List<Game> recommendations;
}
