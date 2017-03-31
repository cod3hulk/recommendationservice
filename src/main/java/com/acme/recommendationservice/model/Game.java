package com.acme.recommendationservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Game
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
