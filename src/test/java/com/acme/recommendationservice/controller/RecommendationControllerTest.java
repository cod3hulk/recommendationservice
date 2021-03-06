package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.service.RecommendationService;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendationController.class)
public class RecommendationControllerTest
{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RecommendationService recommendationService;


    @Test
    public void getRecommendationsByCustomer() throws Exception
    {
        // GIVEN
        Recommendation recommendation = Recommendation.builder()
            .id(1L)
            .customerId(1L)
            .active(true)
            .name("bingo")
            .build();

        when(recommendationService.findByCustomerId(1L, 2)).thenReturn(Collections.singletonList(recommendation));

        // @formatter:off
        // WHEN
        this.mvc.perform(
            get("/customers/1/games/recommendations?count=2")
                .accept(MediaType.APPLICATION_JSON)
        )
        // THEN
        .andExpect(status().isOk());
        // @formatter:on
    }


    @Test
    public void getRecommendationsByCustomer_no_recommendations_found() throws Exception
    {
        // @formatter:off
        // WHEN
        this.mvc.perform(
            get("/customers/1/games/recommendations?count=2")
                .accept(MediaType.APPLICATION_JSON)
        )
        // THEN
        .andExpect(status().isNotFound());
        // @formatter:on
    }


    @Test
    public void uploadFile() throws Exception
    {
        // @formatter:off
        // GIVEN
        String csvData =
            "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC2\",\"REC2\",\"REC3\", REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\n" +
            "\"111111\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\n";

        MockMultipartFile multipartFile = new MockMultipartFile(
            "file",
            "test.txt",
            "text/plain",
            csvData.getBytes());

        // WHEN
        this.mvc.perform(
            fileUpload("/recommendations/upload")
                .file(multipartFile)
        )
        // THEN
        .andExpect(status().isOk());
        // @formatter:on

        verify(recommendationService).saveCsvData(multipartFile);
    }
}