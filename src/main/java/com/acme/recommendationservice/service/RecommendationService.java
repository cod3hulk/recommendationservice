package com.acme.recommendationservice.service;

import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.repository.RecommendationRepository;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RecommendationService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationService.class);

    private RecommendationRepository recommendationRepository;


    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository)
    {
        this.recommendationRepository = recommendationRepository;
    }


    public void saveCsvData(MultipartFile file)
    {
        try (
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)
        )
        {
            parser.getRecords().forEach(record ->
                {
                    Long customerId = Long.valueOf(record.get("CUSTOMER_NUMBER"));
                    boolean recommendationActive = Boolean.valueOf(record.get("RECOMMENDATION_ACTIVE"));
                    for (int i = 1; i <= 10; i++)
                    {
                        Recommendation recommendation = new Recommendation();
                        recommendation.setCustomerId(customerId);
                        recommendation.setGame(record.get("REC" + i));
                        recommendation.setActive(recommendationActive);
                        recommendationRepository.save(recommendation);
                    }

                }
            );
        }
        catch (IOException e)
        {
            LOGGER.error("Error parsing CSV file", e);
        }
    }
}
