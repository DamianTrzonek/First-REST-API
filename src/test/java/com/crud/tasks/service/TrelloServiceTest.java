package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("testId", "testName", false);
        trelloListDtos.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("boardName", "boardId", trelloListDtos);
        trelloBoardDtoList.add(trelloBoardDto);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> testBoardDtoList = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, testBoardDtoList.size());
        trelloBoardDtoList.forEach(trelloBoardDto1 -> {
            assertEquals("boardId" ,trelloBoardDto1.getId());
            assertEquals("boardName", trelloBoardDto1.getName());

            trelloBoardDto1.getLists().forEach(trelloListDto1 -> {
                assertEquals("testId" ,trelloListDto1.getId());
                assertEquals("testName", trelloListDto1.getName());
                assertEquals(false, trelloListDto1.isClosed());
            });
        });
    }

    @Test
    public void shouldCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testName", "this is a test card", "top", "testId");
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("cardId", "testName", "http://testing.com");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(cardDto);
        //When
        CreatedTrelloCardDto testCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals(testCard, cardDto);




    }
}