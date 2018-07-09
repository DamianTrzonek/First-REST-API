package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloBadgesTest {

    @Test
    public void testGetters() {
        //Given
        Trello trello = new Trello(1, 2);
        TrelloAttachmentsByType attachmentsByType = new TrelloAttachmentsByType(trello);
        TrelloBadges trelloBadges = new TrelloBadges(10, attachmentsByType);
        //When
        int badgesVotes = trelloBadges.getVotes();
        TrelloAttachmentsByType testAttachments = trelloBadges.getAttachmentsByType();
        int testTrelloBoard = trelloBadges.getAttachmentsByType().getTrello().getBoard();
        int testTrelloCard = trelloBadges.getAttachmentsByType().getTrello().getCard();
        //Then
        assertEquals(10, badgesVotes);
        assertEquals(testAttachments, attachmentsByType);
        assertEquals(1, testTrelloBoard);
        assertEquals(2, testTrelloCard);
    }

    @Test
    public void getAttachmentsByType() {
    }
}