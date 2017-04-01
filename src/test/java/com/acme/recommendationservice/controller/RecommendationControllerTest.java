package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.repository.RecommendationRepository;
import com.acme.recommendationservice.service.RecommendationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    private RecommendationRepository recommendationRepository;

    @MockBean
    private RecommendationService recommendationService;


    @Test
    public void getRecommendationsByCustomer() throws Exception
    {
        // @formatter:off
        this.mvc.perform(
            get("/customers/1/games/recommendations?count=2")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
        // @formatter:on
    }


    @Test
    public void uploadFile() throws Exception
    {
        // @formatter:off
        String csvData =
            "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC2\",\"REC2\",\"REC3\", REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\n" +
            "\"111111\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\n";

        MockMultipartFile multipartFile = new MockMultipartFile(
            "file",
            "test.txt",
            "text/plain",
            csvData.getBytes());

        this.mvc.perform(
            fileUpload("/recommendations")
                .file(multipartFile)
        )
        .andExpect(status().isOk());
        // @formatter:on
    }
}