package com.acme.recommendationservice.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationControllerDocumentation
{

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Before
    public void setUp()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(this.restDocumentation))
            .build();
    }


    @Test
    @Sql("classpath:db/test-recommendation-data.sql")
    public void getGameRecommendationsByCustomer() throws Exception
    {
        RestDocumentationResultHandler document = document(
            "recommendations-get-by-customer",
            pathParameters(
                parameterWithName("customerId").description("ID of the customer the recommendations are retrieved")
            ),
            requestParameters(
                parameterWithName("count").description("Maximum count of recommendations retrieved")
            ),
            responseFields(
                fieldWithPath("[]").description("Array of recommendations"),
                fieldWithPath("[].id").description("ID of the recommendation"),
                fieldWithPath("[].customerId").description("ID of the customer the recommendation belongs to"),
                fieldWithPath("[].name").description("Name of the recommended name"),
                fieldWithPath("[].active").description("Whether or not the recommendation is active for the customer")
            )
        );

        this.mockMvc.perform(
            get("/customers/{customerId}/games/recommendations?count={count}", 1, 5)
        )
            .andExpect(status().isOk())
            .andDo(document);
    }


    @Test
    public void uploadReommendations() throws Exception
    {
        // @formatter:off
        // @formatter:on
    }

}