package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Getter @Setter
public class Setup {


    public ArrayList<Card> createCards() {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 1; i < 105; i++) {
            Card card = new Card(i, 1);
            if (i == 55) {
                card.setOxHead(7);
            } else if (i % 11 == 0) {
                card.setOxHead(5);
            } else if (i % 10 == 0) {
                card.setOxHead(3);
            } else if (i % 5 == 0) {
                card.setOxHead(2);
            }
            deck.add(card);
        }
        return deck;
    }


    // Fonction pour distribuer les cartes au début
    public void distributionCard(Player player, ArrayList<Card> deck){
        int numberHand = 0; // At first, the players don't have any card
        int index;
        Random random = new Random();
        while (numberHand != 10) {
            index = random.nextInt(deck.size());
            player.getHand().add(deck.get(index));
            deck.remove(index);
            numberHand++;
        }
        Collections.sort(player.getHand());
    }
}
