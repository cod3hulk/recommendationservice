package com.acme.recommendationservice.service;

import com.acme.recommendationservice.exception.RecommendationCsvError;
import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.repository.RecommendationRepository;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static java.util.stream.Collectors.toList;

@Service
public class RecommendationService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationService.class);
    private static final int MAX_NUMBER_OF_RECOMMENDATIONS = 10;

    private RecommendationRepository recommendationRepository;


    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository)
    {
        this.recommendationRepository = recommendationRepository;
    }


    public List<Recommendation> findByCustomerId(Long customerId, Integer limit)
    {
        Pageable pageable = new PageRequest(0, limit);
        return recommendationRepository.findByCustomerId(customerId, pageable);
    }


    @Transactional
    public void saveCsvData(MultipartFile file)
    {
        try (
            InputStreamReader reader = new InputStreamReader(file.getInputStream())
        )
        {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            parser.getRecords().forEach(record ->
                {
                    List<Recommendation> recommendations = mapToRecommendations(record);
                    recommendationRepository.save(recommendations);
                }
            );
        }
        catch (Exception e)
        {
            LOGGER.error("Error processing recommendations csv file", e);
            throw new RecommendationCsvError(e);
        }
    }


    private List<Recommendation> mapToRecommendations(CSVRecord record)
    {
        Long customerId = Long.valueOf(record.get("CUSTOMER_NUMBER"));
        boolean recommendationActive = Boolean.valueOf(record.get("RECOMMENDATION_ACTIVE"));

        return IntStream.rangeClosed(1, MAX_NUMBER_OF_RECOMMENDATIONS)
            .mapToObj(recommendationNumber ->
                Recommendation.builder()
                    .customerId(customerId)
                    .name(record.get("REC" + recommendationNumber))
                    .active(recommendationActive)
                    .build()
            ).collect(toList());
    }

}
