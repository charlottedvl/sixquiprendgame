package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

@Getter
@Setter
public class Serie {
    private int totalHead;
    private Card lastCard;
    private ArrayList<Card> stack;

    public Serie(ArrayList<Card> deck) {
        this.stack = new ArrayList<>();
        Random random = new Random();
        int index = random.nextInt(deck.size());
        this.stack.add(deck.get(index));
        deck.remove(index);
        this.lastCard = stack.get(0);
        this.totalHead = stack.get(0).getOxHead();
    }

    public boolean testNumber () {
        boolean test = true;
        if (this.stack.size() == 5){
            test = false;
        }
        return test;
    }
}
