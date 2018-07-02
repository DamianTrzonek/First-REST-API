package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
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
public class TrelloMapperTestSuite {
@Autowired
private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> boardDto = new ArrayList<>();
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("testId", "testName", false));
        boardDto.add(new TrelloBoardDto("testName", "testId", listDto));

        List<TrelloBoard> boards = new ArrayList<>();
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("testId", "testName", false));
        boards.add(new TrelloBoard("testId", "testName", lists));
        //When
        List<TrelloBoard> testBoard =  trelloMapper.mapToBoards(boardDto);
        //Then
        Assert.assertEquals(testBoard, boards);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("id", "name", false));
        List<TrelloBoard> board = new ArrayList<>();
        board.add(new TrelloBoard("id", "name", list));

        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("id", "name", false));
        List<TrelloBoardDto> boardDto = new ArrayList<>();
        boardDto.add(new TrelloBoardDto("id", "name", listDto));
        //When
        List<TrelloBoardDto> testBoardDto = trelloMapper.mapToBoardsDto(board);
        //Then
        Assert.assertEquals(testBoardDto, boardDto);

    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("id", "name", false));

        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("id", "name", false));
        //When
        List<TrelloList> testList = trelloMapper.mapToList(listDto);
        //Then
        Assert.assertEquals(testList,list);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("id", "name", false));

        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("id", "name", false));
        //When
        List<TrelloListDto> testListDto = trelloMapper.mapToListDto(list);
        //Then
        Assert.assertEquals(testListDto,listDto);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("name", "description", "top", "1");

        TrelloCardDto cardDto = new TrelloCardDto("name", "description", "top", "1");
        //When
        TrelloCardDto testCardDto = trelloMapper.mapToCardDto(card);
        //Then
        Assert.assertEquals(testCardDto,cardDto);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("name", "description", "top", "1");

        TrelloCard card = new TrelloCard("name", "description", "top", "1");
        //When
        TrelloCard testCard = trelloMapper.mapToCard(cardDto);
        //Then
        Assert.assertEquals(testCard, card);
    }
}