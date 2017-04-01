package com.acme.recommendationservice.controller;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class RecommendationControllerDocumentation
{
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private RequestSpecification documentationSpec;

    @Value("${local.server.port}")
    private int port;


    @Before
    public void setUp()
    {
        this.documentationSpec = new RequestSpecBuilder()
            .addFilter(documentationConfiguration(restDocumentation)).build();
    }


    @Test
    @Sql("classpath:db/test-recommendation-data.sql")
    public void getGameRecommendationsByCustomer() throws Exception
    {
        // @formatter:off
        given(this.documentationSpec)
            .port(port)
            .filter(document("get-game-recommendations-by-customer",
                requestParameters(
                    parameterWithName("count").description("Maximum count of recommendations retrieved")
                ),
                pathParameters(
                    parameterWithName("customerId").description("Customer which recommendations are retrieved")
                ),
                responseFields(
                    fieldWithPath("[]").description("Array of recommendations"),
                    fieldWithPath("[].id").description("ID of the recommended game"),
                    fieldWithPath("[].game").description("Name of the recommended game")
                )
            ))
        .when()
            .get("/customers/{customerId}/games/recommendations?count=5", 1)
        .then()
            .assertThat().statusCode(200);
        // @formatter:on
    }


}