package com.isep.sixquiprendgame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void compareToTest() {
        Card cardOne = new Card(1, 5);
        Card cardTwo = new Card(3, 5);
        int compare = cardOne.compareTo(cardTwo);
        assertEquals(-2, compare);
    }
}
