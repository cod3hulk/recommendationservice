package com.acme.recommendationservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Entity
@Builder
public class Recommendation
{
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private String name;
    private boolean active;


    @Tolerate
    Recommendation()
    {
        // default constructor needed by JPA
    }
}
