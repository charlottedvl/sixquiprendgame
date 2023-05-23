package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class HumanPlayer extends Player {
    private String name;

    public HumanPlayer(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.totalOxHead = 0;
    }
}
