package com.acme.recommendationservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Recommendation
{
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private String game;
}
