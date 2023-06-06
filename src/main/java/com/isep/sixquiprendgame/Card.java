package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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

    public int getMinimumStack(ArrayList<Serie>  stacks){
        int number = this.getNumber();
        int indexSerie = -1;
        int actualSerie = -1;
        int minimumDifference = Integer.MAX_VALUE;
        for (Serie serie : stacks){
            actualSerie++;
            if (number > serie.getLastCard().getNumber()){
                int difference = Math.abs(number - serie.getLastCard().getNumber());
                if (difference < minimumDifference) {
                    minimumDifference = difference;
                    indexSerie = actualSerie;
                }
            }
        }
        return indexSerie;
    }
}
