package com.acme.recommendationservice.controller;

import com.acme.recommendationservice.repository.RecommendationRepository;
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
        MockMultipartFile multipartFile = new MockMultipartFile(
            "file",
            "test.txt",
            "text/plain",
            "Spring Framework".getBytes());

        this.mvc.perform(
            fileUpload("/recommendations")
                .file(multipartFile)
        )
        .andExpect(status().isOk());
        // @formatter:on
    }
}