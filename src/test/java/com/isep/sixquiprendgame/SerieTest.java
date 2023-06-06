package com.isep.sixquiprendgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SerieTest {
    @Test
    public void testNumberTest() {
        Setup setup = new Setup();
        ArrayList<Card> deck = setup.createCards();
        Serie stack = new Serie(deck);
        assertEquals(1, stack.getStack().size());
        stack.getStack().add(deck.get(0));
        assertEquals(2, stack.getStack().size());
    }
}
