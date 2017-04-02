package com.acme.recommendationservice.service;

import com.acme.recommendationservice.model.Recommendation;
import com.acme.recommendationservice.repository.RecommendationRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationServiceTest
{
    @InjectMocks
    private RecommendationService objectUnderTest;

    @Mock
    private RecommendationRepository recommendationRepository;


    @Test
    public void saveCsvData() throws Exception
    {
        // GIVEN
        String csvData =
            "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC1\",\"REC2\",\"REC3\",\"REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\n" +
                "\"111111\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"";

        MockMultipartFile multipartFile = new MockMultipartFile(
            "file",
            "test.txt",
            "text/plain",
            csvData.getBytes());

        // WHEN
        objectUnderTest.saveCsvData(multipartFile);

        // THEN
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(recommendationRepository, times(1)).save(captor.capture());

        List<Recommendation> recommendations = captor.getValue();
        long customerId = 111111L;
        assertThat(recommendations, containsInAnyOrder(
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("bingo"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("cashwheel"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("brilliant"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("citytrio"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("crossword"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("sevenwins"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("sudoku"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("sofortlotto"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("hattrick"))
            ),
            allOf(
                hasProperty("customerId", is(customerId)),
                hasProperty("active", is(true)),
                hasProperty("name", is("cashbuster"))
            )
        ));
    }

}