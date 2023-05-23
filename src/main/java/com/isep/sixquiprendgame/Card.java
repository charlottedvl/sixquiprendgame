package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Card {
    private int number;
    private int oxHead;

    public Card(int number, int oxHead){
        this.number = number;
        this.oxHead = oxHead;
    }
}
