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

    public List<Card> createCards(){
        List<Card> stacks = new ArrayList<>();
        for (int i=1; i<105; i++){
            stacks.add(new Card(i, 1));
            if (i == 55){
                stacks.get(i).setOxHead(7);
            } else if (i%11 == 0){
                stacks.get(i).setOxHead(5);
            } else if (i%10 == 0){
                stacks.get(i).setOxHead(3);
            } else if (i%5 == 0){
                stacks.get(i).setOxHead(2);
            }
        } return stacks;
    }

    // Fonction pour distribuer les cartes au début
    public void distributionCard(Player player, List<Card> stacks){
        int numberHand = 0; // At first, the players don't have any card
        int index = 0;
        Random random = new Random();
        while (numberHand != 10) {
            index = random.nextInt(stacks.size()) + 1;
            player.getHand().add(stacks.get(index));
            stacks.remove(index);
            numberHand++;
        }
    }


    // Fonction pour savoir c'est le tour de qui à chque fois?
}
