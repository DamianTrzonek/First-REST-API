package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        //When
        String testApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        assertEquals("https://api.trello.com/1" , testApiEndpoint);
    }

    @Test
    public void getTrelloAppKey() {
        //When
        String testAppKey = trelloConfig.getTrelloAppKey();
        //Then
        assertEquals("95c2492fed8ed9269e7ae543a4ee0a19", testAppKey);
    }

    @Test
    public void getTrelloToken() {
        //When
        String testToken = trelloConfig.getTrelloToken();
        //Then
        assertEquals("c5a8575a7e63ae5a0e28899c54cfaecd5bc1f98afdf9ea300f877843b3e0e148", testToken);
    }

    @Test
    public void getUsername() {
        //When
        String testUsername = trelloConfig.getUsername();
        //Then
        assertEquals("damiantrzonek", testUsername);
    }
}