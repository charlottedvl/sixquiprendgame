package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter @Setter
public class Setup {

    private Board board;

    public Setup(){
        this.board = new Board();
    }

    public ArrayList<Card> createCards() {
        ArrayList<Card> stacks = new ArrayList<>();
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
            stacks.add(card);
        }
        return stacks;
    }


    // Fonction pour distribuer les cartes au début
    public void distributionCard(Player player, ArrayList<Card> stacks){
        int numberHand = 0; // At first, the players don't have any card
        int index = 0;
        Random random = new Random();
        while (numberHand != 10) {
            index = random.nextInt(stacks.size());
            player.getHand().add(stacks.get(index));
            stacks.remove(index);
            numberHand++;
        }
    }


    // Fonction pour savoir c'est le tour de qui à chque fois?
}
