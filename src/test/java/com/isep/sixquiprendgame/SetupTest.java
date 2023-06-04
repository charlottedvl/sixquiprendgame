package com.isep.sixquiprendgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SetupTest {

    @Test
    public void createCardsTest() {
        Setup setup = new Setup();
        ArrayList<Card> deck = setup.createCards();
        assertEquals(104, deck.size());
    }

    @Test
    public void distributionCardTest() {
        HumanPlayer player = new HumanPlayer("Test");
        Setup setup = new Setup();
        ArrayList<Card> deck = setup.createCards();
        setup.distributionCard(player, deck);
        assertEquals(10, player.getHand().size());
        assertEquals(94, deck.size());
        assertTrue(player.getHand().get(0).getNumber() < player.getHand().get(1).getNumber());
    }
}
