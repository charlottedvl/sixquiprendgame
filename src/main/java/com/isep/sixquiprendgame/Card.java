package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Card implements Comparable<Object> {
    private int number;
    private int oxHead;


    public Card(int number, int oxHead){
        this.number = number;
        this.oxHead = oxHead;
    }

    @Override
    public int compareTo(Object o) {
        Card card = (Card) o;
        return this.number - card.number ;
    }
}
