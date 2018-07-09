package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
@Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("Id", "name", false);
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("BoardId", "Some name", trelloLists);

        TrelloBoard trelloBoardWithTest = new TrelloBoard("Test", "Test", trelloLists);
        trelloBoardList.add(trelloBoard);
        trelloBoardList.add(trelloBoardWithTest);
        //When
       List<TrelloBoard> filteredTrelloBoardList = trelloValidator.validateTrelloBoards(trelloBoardList);
        //Then
        assertEquals(1, filteredTrelloBoardList.size());
    }
}