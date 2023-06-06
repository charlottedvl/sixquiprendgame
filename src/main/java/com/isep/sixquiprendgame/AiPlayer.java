package com.isep.sixquiprendgame;

import java.util.ArrayList;

public class AiPlayer extends Player {


    public AiPlayer(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.totalOxHead = 0;
        this.ranking = 0;
    }
}
